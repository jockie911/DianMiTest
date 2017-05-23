package com.example.objLoader.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.bean.Temp;
import com.example.objLoader.global.BaseApp;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.Logger;
import com.example.objLoader.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI msgApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msgApi = WXAPIFactory.createWXAPI(this, Constants.WX_ID);
        msgApi.handleIntent(getIntent(),this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        ToastUtils.show(((SendAuth.Resp) baseResp).code + "");
        switch (baseResp.errCode){
            case BaseResp.ErrCode.ERR_OK:
                if(TextUtils.equals(IConstant.WX_REQ_STATE,((SendAuth.Resp) baseResp).state)){
                    String code = ((SendAuth.Resp) baseResp).code;

                    ToastUtils.show(code);
                    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3666f68c67eb1f86";
                    Request<String> loginRequest = NoHttp.createStringRequest(url, RequestMethod.GET);
                    loginRequest.add("secret", "34a8ceb3d701f3f74427d7d9b9508742");
                    loginRequest.add("code", code);
                    loginRequest.add("grant_type", "authorization_code");

                    CallServer.getInstance().add(BaseApp.getContext(), loginRequest, callBack, Constants.REGISTER_WHAT, false, false,Temp.class);
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED :
                ToastUtils.show("已拒绝授权");
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL :
                ToastUtils.show(R.string.cancel);
                finish();
                break;
        }
    }

    private HttpCallBack<Temp> callBack = new HttpCallBack<Temp>() {

        @Override
        public void onSucceed(int what, Response<Temp> response) {
            super.onSucceed(what, response);
            String s = response.get().toString();
            Toast.makeText(BaseApp.getContext(),s,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onSucceed(int what, Temp bean) {
            super.onSucceed(what, bean);
        }

        public void onFailed(int what, String errorInfo) {
            Toast.makeText(BaseApp.getContext(),errorInfo,Toast.LENGTH_LONG).show();
        };
    };
}
