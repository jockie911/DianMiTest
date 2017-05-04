package com.example.objLoader.fragment;

import com.example.objLoader.R;
import com.example.objLoader.utils.Constants;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyLogModelFragment extends BaseFragment {

	private View myLogModel;
	private String objUrl;

	/** 腾讯X5内核WebView */
	private com.tencent.smtt.sdk.WebView tencent_mylog_model_webview;
	private com.tencent.smtt.sdk.WebSettings settings;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		myLogModel = inflater.inflate(R.layout.fragment_my_log_model, null);
		objUrl = getActivity().getIntent().getExtras().getString("objUrl");
		
		initView();
		initData();
		setEvent();

		return myLogModel;

	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	protected void initView() {

		tencent_mylog_model_webview = (com.tencent.smtt.sdk.WebView) myLogModel
				.findViewById(R.id.tencent_mylog_model_webview);

	}

	@Override
	protected void initData() {
		
		String url = Constants.SERVER + objUrl;
		
		tencent_mylog_model_webview.loadUrl("http://114.55.145.129/somatometry/show3d.php?objname=57a046aa3b8aa.obj");
		settings = tencent_mylog_model_webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		// 设置可以支持缩放
		settings.setSupportZoom(true);
		settings.setDomStorageEnabled(true);

		tencent_mylog_model_webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

	}

	@Override
	protected void setEvent() {

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		tencent_mylog_model_webview.removeAllViews();
		tencent_mylog_model_webview.destroy();
		tencent_mylog_model_webview.destroyDrawingCache();

	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

	}

	public void finish(View v) {
		getActivity().finish();
	}
}
