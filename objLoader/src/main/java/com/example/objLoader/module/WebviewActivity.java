package com.example.objLoader.module;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;

public class WebviewActivity extends BaseActivity {

    @Bind(R.id.tencent_model_webview)
    WebView webView;
    private WebSettings settings;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_webview;
    }

    @Override
    protected boolean isHavaBaseTitleBar() {
        return true;
    }

    @Override
    protected void initData() {
        initSetting();

        String title = getIntent().getStringExtra(IConstant.TITLE);
        String url = getIntent().getStringExtra(IConstant.URL);
        tvTitle.setText(title);
        webView.loadUrl(url);
    }

    private void initSetting() {
        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onDestroy() {
        if(webView != null){
            webView.removeAllViews();
            webView.destroy();
            webView.destroyDrawingCache();
        }
        super.onDestroy();
    }
}
