package com.example.objLoader.module.measure;


import android.content.Intent;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.bean.event.PicPathEvent;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.login.presenter.LoginPresent;
import com.example.objLoader.module.measure.present.FrontSidePresenter;
import com.example.objLoader.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class FrontPicActivity extends BaseActivity implements FrontSideView{

	@Bind(R.id.iv_target)
	protected ImageView ivTarget;

	public static FrontPicActivity activity;

	protected int GENDER ;
	protected FrontSidePresenter frontSidePresenter;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_front_pic;
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected void initData() {
		activity = this;
		if(!EventBus.getDefault().isRegistered(this))
			EventBus.getDefault().register(this);
		tvTitle.setText(R.string.front_Pic);
		GENDER = getIntent().getIntExtra(IConstant.GENDER, 0);

		frontSidePresenter = new FrontSidePresenter(this,this);
		frontSidePresenter.initBmpShow(ivTarget);
	}

	@Override
	@OnClick({R.id.btn_front_camera,R.id.btn_front_album,R.id.tv_next_step})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
		case R.id.btn_front_camera:
			requestPermissions(true);
			showCameraFragment(true);
			break;
		case R.id.btn_front_album:
			openAlbumChoosePic();
			break;
		case R.id.tv_next_step:
			frontSidePresenter.nextStep();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == IConstant.ALBUM_REQUEST_CODE && resultCode == RESULT_OK) {
			String albumPath = getRealPathFromURI(data.getData()); // 图片文件路径
			frontSidePresenter.checkAlbumPicContainsData(albumPath);
		}
	}

	public void showCameraFragment(boolean isShowCamaraFragment){
		frontSidePresenter.showCameraFragment(isShowCamaraFragment);
	}

	/**
	 * 正面选择相机成功返回照片
	 */
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void setPicSuccessAndCloseCameraSurfaceView(PicPathEvent obj){
		if(obj.isFrontTakePhoto()){
			Bitmap bitmap = BaseApp.getInstance().getCameraBitmap();
			if (bitmap != null)
				ivTarget.setImageBitmap(bitmap);
			showCameraFragment(false);
		}
	}

	@Override
	protected void onDestroy() {
		if(EventBus.getDefault().isRegistered(this))
			EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	@Override
	public int getGender() {
		return GENDER;
	}

	@Override
	public boolean isFrontPic() {
		return true;
	}

	@Override
	public void resultAlbumPicSuccess(String albumPath) {
		if(ivTarget != null)
			Glide.with(this).load(albumPath).into(ivTarget);
		SPUtils.getInstance().putString(isFrontPic() ? IConstant.FRONT_PIC_PATH : IConstant.SIDE_PIC_PATH, albumPath);
	}

	@Override
	public void resultAlbumPicError() {
		frontSidePresenter.showErrorDialog();
	}

	@Override
	public void openAlbumChoosePic() {
		Intent photoIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
		startActivityForResult(photoIntent, IConstant.ALBUM_REQUEST_CODE);
	}
}
