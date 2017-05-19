package com.example.objLoader.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.bean.PicPathEvent;
import com.example.objLoader.fragment.PhotoCommandFragment;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.global.BaseApp;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class FrontPicActivity extends BaseActivity {

	@Bind(R.id.iv_front)
	ImageView iv_front;
	@Bind(R.id.tv_next_step)
	TextView tv_next_step;
	@Bind(R.id.btn_front_camera)
	Button btn_front_camera;
	@Bind(R.id.btn_front_album)
	Button btn_front_album;

	private String front_pic_path = "";
	public static FrontPicActivity activity;

	private String albumPath;
	private String frontPath;
	private int GENDER ;
	private PhotoCommandFragment photoCommandFragment;

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

		frontPath = SPUtils.getInstance(mContext).getString(IConstant.FRONT_PIC_PATH);
		if(!TextUtils.isEmpty(frontPath)){
			Glide.with(this).load(frontPath).into(iv_front);
		}
		GENDER = getIntent().getIntExtra(IConstant.GENDER, 0);
	}

	@Override
	@OnClick({R.id.btn_front_camera,R.id.btn_front_album,R.id.tv_next_step})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
		case R.id.btn_front_camera:
			requestPermissions(true);
			initShowFragment(true);
			break;
		case R.id.btn_front_album:
			Intent photoIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
			startActivityForResult(photoIntent, IConstant.ALBUM_REQUEST_CODE);
			break;
		case R.id.tv_next_step:
			frontPath = SPUtils.getInstance(mContext).getString(IConstant.FRONT_PIC_PATH);
			if(TextUtils.isEmpty(frontPath)){
				ToastUtils.show(R.string.selector_front_pic);
				return;
			}
			Intent intent = new Intent(mContext, SidePicActivity.class);
			intent.putExtra(IConstant.GENDER,GENDER);
			startActivity(intent);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == IConstant.ALBUM_REQUEST_CODE && resultCode == RESULT_OK) {

			albumPath = getRealPathFromURI(data.getData()); // 图片文件路径
			
			front_pic_path = albumPath;
			
			Glide.with(mContext).load(albumPath).into(iv_front);
			SPUtils.getInstance(mContext).putString(IConstant.FRONT_PIC_PATH, front_pic_path);
		}
	}

	public void initShowFragment(boolean isShow){
		if(photoCommandFragment == null)
			photoCommandFragment = new PhotoCommandFragment(true,GENDER);
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		if(isShow){
			if(!photoCommandFragment.isAdded()){
				fragmentTransaction.add(R.id.content_container,photoCommandFragment);
			}else{
				fragmentTransaction.show(photoCommandFragment);
			}
		}else{
			fragmentTransaction.hide(photoCommandFragment);
		}
		fragmentTransaction.commit();
	}

	/**
	 * 正面选择相机成功返回照片
	 */
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void setPicSuccessAndCloseCameraSurfaceView(PicPathEvent obj){
//		File file = new File(obj.getPath());
//
//		long s = file.length();
//
//		String absolutePath = file.getAbsolutePath();
//		if(file.length() > 0){
//
//			Glide.with(this).load(file).into(iv_front);
//		}
//		initShowFragment(false);

		if(obj.isFrontTakePhoto()){
			Bitmap bitmap = BaseApp.getInstance().getCameraBitmap();

			if (bitmap != null) {
				iv_front.setImageBitmap(bitmap);
			}
			initShowFragment(false);
		}
	}

	@Override
	protected void onDestroy() {
		if(EventBus.getDefault().isRegistered(this))
			EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	@SuppressLint("Override") @Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		//定位到这个权限
		if (requestCode==1){
			if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
                ToastUtils.show("权限申请成功");

			}else{
               ToastUtils.show("权限申请失败");
			}
		}

	}
}
