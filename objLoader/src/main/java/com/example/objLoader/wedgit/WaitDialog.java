package com.example.objLoader.wedgit;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.objLoader.R;
import com.example.objLoader.utils.DensityUtil;


public class WaitDialog extends ProgressDialog {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wait);

		WindowManager.LayoutParams attributes = getWindow().getAttributes();
		attributes.width = DensityUtil.dip2px(getContext(),150);
		attributes.height = DensityUtil.dip2px(getContext(),150);
		getWindow().setAttributes(attributes);


	}
	
	public WaitDialog(Context context) {
		super(context);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setCanceledOnTouchOutside(false);
//		setProgressStyle(STYLE_SPINNER);
//		setTitle("请求中，请稍候。。。");
	}
}
