package com.example.objLoader.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.objLoader.bean.Temp;
import com.example.objLoader.bean.WXUserInfoBean;
import com.example.objLoader.bean.WxLoginSuccessEvent;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.net.RestClient;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
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

import java.util.HashMap;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


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

        RestClient.instance().wxLogin(urlStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Temp>() {
                    @Override
                    public void call(Temp temp) {
                        getUserInfo(temp.getAccess_token(),temp.getOpenid());
                    }
                });
    }

    private void getUserInfo(String accessToken,String openId){
        RestClient.instance().wxUserInfo("https://api.weixin.qq.com/sns/userinfo",accessToken,openId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WXUserInfoBean>() {
                    @Override
                    public void call(WXUserInfoBean wxUserInfoBean) {
                        login(wxUserInfoBean);
                    }
                });
    }

    private void login(final WXUserInfoBean bean) {
        RestClient.instance().checkWXLogin(Constants.WX_LOGIN,
                bean.getUnionid(),
                bean.getNickname(),
                bean.getHeadimgurl(),
                bean.getSex(),
                1,
                "18297508272")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<WXUserInfoBean>() {
            @Override
            public void call(WXUserInfoBean wxUserInfoBean) {
                if(TextUtils.equals("0",wxUserInfoBean.getIserror())){
                    EventBus.getDefault().post(new WxLoginSuccessEvent());
                }else{
                    ToastUtils.show("error");
                }
                finish();
            }
        });
    }
}
