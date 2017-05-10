package com.example.objLoader.global;

import cn.smssdk.SMSSDK;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

public class BaseApp extends Application {

	private static BaseApp instance;
	private static Context context;

	public static int mScreenWidth = 0;
	public static int mScreenHeight = 0;

	private Bitmap mCameraBitmap;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		context = getApplicationContext();
		// 初始化NoHttp
		NoHttp.initialize(this);
		// 打开NoHttp的调试模式
		Logger.setDebug(false);
		// 设置NoHttp的日志的tag
		Logger.setTag("Pan Shuai");
		
		SMSSDK.initSDK(this, "157ea230e470e", "b56bd81e865fa5e575750c6aade55505");


		DisplayMetrics mDisplayMetrics = getApplicationContext().getResources()
				.getDisplayMetrics();
		BaseApp.mScreenWidth = mDisplayMetrics.widthPixels;
		BaseApp.mScreenHeight = mDisplayMetrics.heightPixels;
	}

	public static BaseApp getInstance() {
		return instance;
	}

	public static Context getContext(){
		return context;
	}







	public Bitmap getCameraBitmap() {
		return mCameraBitmap;
	}

	public void setCameraBitmap(Bitmap mCameraBitmap) {
		if (mCameraBitmap != null) {
			recycleCameraBitmap();
		}
		this.mCameraBitmap = mCameraBitmap;
	}

	public void recycleCameraBitmap() {
		if (mCameraBitmap != null) {
			if (!mCameraBitmap.isRecycled()) {
				mCameraBitmap.recycle();
			}
			mCameraBitmap = null;
		}
	}
}
