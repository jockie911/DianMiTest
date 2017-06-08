package com.example.objLoader.module.measure;


import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.bean.event.PicPathEvent;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.present.FrontSidePresenter;
import com.example.objLoader.present.view.IFrontSideView;
import com.example.objLoader.utils.SPUtils;
import com.lilei.springactionmenu.ActionMenu;
import com.lilei.springactionmenu.OnActionItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class FrontPicActivity extends BaseActivity implements IFrontSideView, OnActionItemClickListener {

	@Bind(R.id.iv_target)
	protected ImageView ivTarget;
	@Bind(R.id.tv_right_title_bar)
	TextView tvRightTitleBar;
	@Bind(R.id.actionMenu)
	ActionMenu actionMenu;
	@Bind(R.id.v_bg)
	View vBg;

	protected int GENDER ;
	protected FrontSidePresenter frontSidePresenter;
	private Bitmap bitmap;

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
//		activity = this;
		if(!EventBus.getDefault().isRegistered(this))
			EventBus.getDefault().register(this);
		tvTitle.setText(R.string.front_Pic);
		GENDER = getIntent().getIntExtra(IConstant.GENDER, 0);

		tvRightTitleBar.setText(getResources().getString(R.string.next));
		frontSidePresenter.initBmpShow(ivTarget);

		actionMenu.addView(R.drawable.xc);
		actionMenu.addView(R.drawable.xj);
		actionMenu.setItemClickListener(this);
	}

	@Override
	protected BasePresenter initPresenter() {
		frontSidePresenter = new FrontSidePresenter(this);
		frontSidePresenter.attachView(this);
		return frontSidePresenter;
	}

	@Override
	@OnClick({/*R.id.btn_front_camera,R.id.btn_front_album,*/R.id.tv_right_title_bar,R.id.v_bg})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
	/*	case R.id.btn_front_camera:
			requestPermissions(true);
			showCameraFragment(true);
			break;
		case R.id.btn_front_album:
			openAlbumChoosePic();
			break;*/
		case R.id.tv_right_title_bar:
			frontSidePresenter.nextStep();
			break;
		case R.id.v_bg:
			if(actionMenu != null && actionMenu.isOpen()){
				setViewBackground(R.color.transparent);
				actionMenu.closeMenu();
			}
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
			bitmap = BaseApp.getInstance().getCameraBitmap();
			if (bitmap != null)
				ivTarget.setImageBitmap(bitmap);
			showCameraFragment(false);
		}
	}

	@Override
	protected void onResume() {
		setViewBackground(R.color.transparent);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		if(actionMenu != null && actionMenu.isOpen())
			actionMenu.closeMenu();
		if(EventBus.getDefault().isRegistered(this))
			EventBus.getDefault().unregister(this);
		super.onDestroy();
		if(bitmap != null){
			bitmap.recycle();
			bitmap = null;
		}
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

	@Override
	public void onItemClick(int i) {
		if(i == 0){
			if(actionMenu != null && actionMenu.isOpen()){ //此处设计的有问题，状态改变发生在点击之前
				setViewBackground(R.color.transparent);
				actionMenu.openMenu();
			}else{
				setViewBackground(R.color.fab_bg_color);
				actionMenu.closeMenu();
			}
		}else if(i == 1){
			openAlbumChoosePic();
		}else if(i == 2){
			showCameraFragment(true);
		}
	}

	@Override
	public void onAnimationEnd(boolean b) {

	}

	@Override
	protected void onPause() {
		super.onPause();
		if(actionMenu != null && actionMenu.isOpen())
			actionMenu.closeMenu();
	}

	public void setViewBackground(int colorResID){
		vBg.setBackgroundColor(getResources().getColor(colorResID));
	}
}
