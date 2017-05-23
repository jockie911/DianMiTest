package com.example.objLoader.module.login.imple;

import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.global.BaseApp;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.utils.Utils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

/**
 * Created by yc on 2017/5/22.
 */

public class LoginModelImple<T extends BaseActivity> implements LoginModel {

    private Request<String> loginRequest;
    private OnLoginListener listener;
    private int count;

    @Override
    public void login(String phoneNum, String pws, OnLoginListener listener) {
        if (phoneNum.equals("") || pws.equals("")) {
            ToastUtils.show(R.string.login_user_pwd_not_null);
            return;
        }
        this.listener = listener;
        loginRequest = NoHttp.createStringRequest(Constants.LOGIN, RequestMethod.POST);
        loginRequest.add(IConstant.MOBILE, phoneNum);
        loginRequest.add(IConstant.PASSWORD, Utils.MD5(pws));
        loginRequest.add(IConstant.STRING, Utils.MD5(phoneNum + Constants.MD5_KEY + Utils.MD5(pws)));
        CallServer.getInstance().add(BaseApp.getContext(), loginRequest, callBack, Constants.REGISTER_WHAT, false, false,BaseRequestBean.class);
    }

    private HttpCallBack<BaseRequestBean> callBack = new HttpCallBack<BaseRequestBean>() {

        @Override
        public void onSucceed(int what, Response<BaseRequestBean> response) {
            super.onSucceed(what, response);
        }

        public void onSucceed(int what, BaseRequestBean bean) {
            listener.onSuccess(bean);
        };

        public void onFailed(int what, String errorInfo) {
            listener.onFailed(errorInfo);
        };
    };

    public void changeEdittextPwdStatus(EditText etPassword, ImageView ivEyePwd) {
        count ++;
        ivEyePwd.setImageResource(count % 2 == 1 ? R.drawable.opened_eye:R.drawable.closed_eye);
        etPassword.setInputType(count % 2 == 1 ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        String s = etPassword.getText().toString();
        etPassword.setSelection(TextUtils.isEmpty(s)? 0 : s.length());
    }

    public void loginWX(T activity) {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = IConstant.WX_REQ_SCOPE;
        req.state = IConstant.WX_REQ_STATE;
        IWXAPI wxapi = WXAPIFactory.createWXAPI(activity, Constants.WX_ID,true);
        wxapi.sendReq(req);
    }
}
