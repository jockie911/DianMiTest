package com.example.objLoader.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.objLoader.bean.Temp;
import com.example.objLoader.bean.WXUserInfoBean;
import com.example.objLoader.bean.WxLoginSuccessEvent;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.personInfo.AccountInfoActivity;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.GsonTools;
import com.example.objLoader.utils.SPUtils;
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

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;


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
        switch (baseResp.errCode){
            case BaseResp.ErrCode.ERR_OK:
                if(TextUtils.equals(IConstant.WX_REQ_STATE,((SendAuth.Resp) baseResp).state)){
                    String code = ((SendAuth.Resp) baseResp).code;
                    ToastUtils.show(code);
                    getOpenID(code);
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED :
                ToastUtils.show("已拒绝授权");
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL :
                ToastUtils.show("已取消");
                finish();
                break;
        }
    }

    private void getOpenID(String code) {
        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Constants.WX_ID+"&secret="+Constants.WX_SECRET+
                "&code="+code+"&grant_type=authorization_code";

        Request<String> loginRequest = NoHttp.createStringRequest(urlStr, RequestMethod.GET);
        CallServer.getInstance().add(this, loginRequest, new HttpCallBack<Object>() {

            private String penid;
            private String access_token;

            @Override
            public void onSucceed(int what, Response<Object> response) {
                String data = response.get().toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    if(jsonObject.has("access_token")) {
                        access_token = jsonObject.getString("access_token");
                    }

                    if(jsonObject.has("openid")){
                        penid = jsonObject.getString("openid");
                    }
                    getUserInfo(access_token,penid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what) {
                super.onFailed(what);
                ToastUtils.show("未知错误");
                finish();
            }
        }, 10000, false, false, Temp.class);
    }

    private void getUserInfo(String accessToken,String openId){
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken +"&openid=" + openId;
        Request<String> loginRequest = NoHttp.createStringRequest(url, RequestMethod.GET);
        CallServer.getInstance().add(this, loginRequest, new HttpCallBack<Object>() {
            @Override
            public void onSucceed(int what, Response<Object> response) {
                login(response.get().toString());
            }

            @Override
            public void onFailed(int what) {
                super.onFailed(what);
                ToastUtils.show("未知错误");
                finish();
            }
        }, 10000, false, false, Temp.class);
    }

    private void login(String s) {
        WXUserInfoBean bean = GsonTools.changeGsonToBean(s, WXUserInfoBean.class);
        Request<String> loginRequest = NoHttp.createStringRequest(Constants.WX_LOGIN, RequestMethod.POST);
        loginRequest.add("uid",bean.getUnionid());
        loginRequest.add("name",bean.getNickname());
        loginRequest.add("iconurl",bean.getHeadimgurl());
        loginRequest.add("gender",bean.getSex());
        loginRequest.add("login_type",1);
        loginRequest.add("mobile","18297508272");
        CallServer.getInstance().add(this, loginRequest, new HttpCallBack<Object>() {
            @Override
            public void onSucceed(int what, Response<Object> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.get().toString());
                    if(jsonObject.has("iserror")){
                        String iserror = jsonObject.getString("iserror");
                        if("0".equals(iserror)){
                            EventBus.getDefault().post(new WxLoginSuccessEvent());
                        }else{
                            ToastUtils.show(iserror);
                        }
                    }
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what) {
                super.onFailed(what);
                ToastUtils.show("未知错误");
                finish();
            }
        }, Constants.REGISTER_WHAT, false, false, Temp.class);
    }
}
