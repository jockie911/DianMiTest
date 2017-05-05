package com.example.objLoader.fragment;


import com.example.objLoader.R;
import com.example.objLoader.bean.MeasureInfo;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.google.gson.Gson;
import com.tencent.smtt.sdk.WebSettings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ModelFragment extends BaseFragment {

	private View view;
	private Gson gson = new Gson();
	private MeasureInfo measureInfo;
	private String objUrl;
	/**腾讯X5内核WebView*/
	private com.tencent.smtt.sdk.WebView tencent_model_webview;
	private com.tencent.smtt.sdk.WebSettings settings;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_model, null);

		String strJson = SharedPreferencesDAO.getInstance(getActivity()).getString("json");
//		objUrl = getActivity().getIntent().getExtras().getString("objUrl");

		objUrl = "http://www.baidu.com";
//		if(strJson.length() <= 0 || strJson.equals("")){
//			startActivity(new Intent(getActivity(), MeasureWeightAndHeightActivity.class));
//			getActivity().finish();
//		}else{
//			measureInfo = gson.fromJson(strJson, MeasureInfo.class);
//			objUrl = measureInfo.getData().getThreeshowurl();
			 initView();
			 initData();
			 return view;
//		}

//		 return view;
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	protected void initView() {
		tencent_model_webview = (com.tencent.smtt.sdk.WebView) view.findViewById(R.id.tencent_model_webview);
		settings = tencent_model_webview.getSettings();
		settings.setJavaScriptEnabled(true);    
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		// 设置可以支持缩放
		settings.setSupportZoom(true);
		settings.setDomStorageEnabled(true);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void initData() {
		//http://114.55.145.129/somatometry/show3d.php?objname=58352f06f06ad.obj
		String url = /*Constants.SERVER + objUrl;*/ "http://114.55.145.129/somatometry/show3d.php?objname=58352f06f06ad.obj";
		Log.e("url", url);
		tencent_model_webview.loadUrl(url);
		
	
		
//		tencent_model_webview.setWebViewClient(new WebViewClient() {    
//				@Override 
//				public boolean shouldOverrideUrlLoading(WebView view, String url) {    
//	                view.loadUrl(url);    
//	                return true;    
//	            }    
//	    });
		
	}
	
	@Override
	protected void setEvent() {

	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {

		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		tencent_model_webview.removeAllViews();
		tencent_model_webview.destroy();
		tencent_model_webview.destroyDrawingCache();
		
	}

}
