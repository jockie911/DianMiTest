package com.example.objLoader.global;

import cn.smssdk.SMSSDK;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import android.app.Application;

public class MyAPP extends Application {

	private static MyAPP instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		// 初始化NoHttp
		NoHttp.initialize(this);
		// 打开NoHttp的调试模式
		Logger.setDebug(false);
		// 设置NoHttp的日志的tag
		Logger.setTag("Pan Shuai");
		
		SMSSDK.initSDK(this, "157ea230e470e", "b56bd81e865fa5e575750c6aade55505");

	}

	public static MyAPP getInstance() {
		return instance;
	}
}
