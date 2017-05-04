package com.example.objLoader.utils;



import com.example.objLoader.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;


public class WaitDialog extends ProgressDialog {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wait);
	}
	
	public WaitDialog(Context context) {
		super(context);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setCanceledOnTouchOutside(false);
//		setProgressStyle(STYLE_SPINNER);
//		setTitle("请求中，请稍候。。。");
	}

}
