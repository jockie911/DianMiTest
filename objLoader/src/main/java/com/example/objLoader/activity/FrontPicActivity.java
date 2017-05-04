package com.example.objLoader.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.Utils;

import java.io.File;

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

	private String height,weight,front_pic_path = "";
	public static FrontPicActivity activity;

	private Uri imageUriFromCamera;
	private static final int CAMERA_REQUEST_CODE = 1,PHOTO_REQUEST_CODE = 2;
	/**拍摄照片地址*/
	private String photoPath;
	/**相册照片地址*/
	private String albumPath;
	private String frontPath;

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
		tvTitle.setText(R.string.front_Pic);
		height = getIntent().getExtras().getString("height");
		weight = getIntent().getExtras().getString("weight");

		frontPath = SharedPreferencesDAO.getInstance(mContext).getString("frontPicPath");
		if(!TextUtils.isEmpty(frontPath)){
			Glide.with(this).load(new File(frontPath)).into(iv_front);
		}
	}

	@Override
	@OnClick({R.id.btn_front_camera,R.id.btn_front_album,R.id.tv_next_step})
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_front_camera:

			
			imageUriFromCamera = Utils.createImagePathUri(mContext);
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
			
			break;

		case R.id.btn_front_album:

			Intent photoIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
			startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
			break;
		case R.id.tv_next_step:
			frontPath = SharedPreferencesDAO.getInstance(mContext).getString("frontPicPath");
			if(frontPath.equals("") || frontPath.length() <= 0){
				Toast.show(R.string.selector_front_pic);
				return;
			}else{
				front_pic_path = frontPath;
			}

			Intent intent = new Intent(mContext, SidePicActivity.class);
			intent.putExtra("height", height);
			intent.putExtra("weight", weight);
			intent.putExtra("front_pic_path", front_pic_path);
			startActivity(intent);

			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

			photoPath = getRealPathFromURI(imageUriFromCamera);
			
			front_pic_path = photoPath;
			
			Glide.with(mContext).load(photoPath).into(iv_front);
			
			SharedPreferencesDAO.getInstance(mContext).putString("frontPicPath", front_pic_path);

		}
		if (requestCode == PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {

			albumPath = getRealPathFromURI(data.getData()); // 图片文件路径
			
			front_pic_path = albumPath;
			
			Glide.with(mContext).load(albumPath).into(iv_front);
			SharedPreferencesDAO.getInstance(mContext).putString("frontPicPath", front_pic_path);

		}

	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		//继承了Activity的onTouchEvent方法，直接监听点击事件
		 if(event.getAction() == MotionEvent.ACTION_DOWN) {
		  //当手指按下的时候
		  x1 = event.getX();
		  y1 = event.getY();
		 }
		 if(event.getAction() == MotionEvent.ACTION_UP) {
		  //当手指离开的时候
		  x2 = event.getX();
		  y2 = event.getY();
		  if(y1 - y2 > 50) {
			  // 向上滑;
		  } else if(y2 - y1 > 50) {
			  // 向下滑
		  } else if(x1 - x2 > 50) {
			  // 向左滑
			  frontPath = SharedPreferencesDAO.getInstance(mContext).getString("frontPicPath");
			  if(frontPath.equals("") || frontPath.length() <= 0){
					Toast.show(R.string.selector_front_pic);
				}else{
					front_pic_path = frontPath;
					Intent intent = new Intent(mContext, SidePicActivity.class);
					intent.putExtra("height", height);
					intent.putExtra("weight", weight);
					intent.putExtra("front_pic_path", front_pic_path);
					startActivity(intent);
				}
				
			  
		  } else if(x2 - x1 > 50) {
			  // 向右滑
			  		finish();
		  }
		 }
		
		return super.onTouchEvent(event);
	}

	public void finish(View v){
		finish();
	}
	
}
