package com.example.objLoader.fragment;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.example.objLoader.R;
import com.example.objLoader.activity.DetailMeasureSizeActivity;
import com.example.objLoader.utils.Constants;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;

public class ModelFragment extends com.example.objLoader.global.BaseFragment {

	@Bind(R.id.tencent_model_webview)
	WebView tencent_model_webview;
	private com.tencent.smtt.sdk.WebSettings settings;
	
	@Override
	protected View inflater(LayoutInflater inflater) {
		return inflater.inflate(R.layout.fragment_model, null);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void initData() {
		settings = tencent_model_webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		// 设置可以支持缩放
		settings.setSupportZoom(true);
		settings.setDomStorageEnabled(true);
		tencent_model_webview.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
	                view.loadUrl(url);
	                return true;
	            }
	    });


		if(getActivity() != null){
			String dTreeUrl = ((DetailMeasureSizeActivity) getActivity()).get3DTreeUrl();
			tencent_model_webview.loadUrl(Constants.SERVER + dTreeUrl);
		}
	}

	@Override
	public void onDestroy() {
		if(tencent_model_webview != null){
			tencent_model_webview.removeAllViews();
			tencent_model_webview.destroy();
			tencent_model_webview.destroyDrawingCache();
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {

	}
}
