package com.example.objLoader.module.measure;

import android.graphics.Bitmap;

import com.example.objLoader.R;
import com.example.objLoader.bean.event.PicPathEvent;
import com.example.objLoader.base.BaseApp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SidePicActivity extends FrontPicActivity implements IFrontSideView {

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_side_pic;
	}

	@Override
	protected void initData() {
		super.initData();
		tvTitle.setText(R.string.side_pic);
	}

	/**
	 * 正面选择相机成功返回照片
	 */
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void setPicSuccessAndCloseCameraSurfaceView(PicPathEvent obj){
		if(!obj.isFrontTakePhoto()){
			Bitmap bitmap = BaseApp.getInstance().getCameraBitmap();
			if (bitmap != null) {
				ivTarget.setImageBitmap(bitmap);
			}
			showCameraFragment(false);
		}
	}

	@Override
	public boolean isFrontPic() {
		return false;
	}
}
