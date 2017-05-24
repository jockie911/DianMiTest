package com.example.objLoader.module.login.presenter;

import android.widget.EditText;
import android.widget.ImageView;

import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.login.imple.LoginView;
import com.example.objLoader.module.login.imple.OnLoginListener;
import com.example.objLoader.module.login.imple.LoginModelImple;
import com.example.objLoader.utils.Logger;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;

/**
 * Created by yc on 2017/5/22.
 */

public class LoginPresent<T extends BaseActivity> implements OnLoginListener{

    private T activity;
    private LoginView loginView;
    private LoginModelImple loginModelImple;

    public LoginPresent(T activity,LoginView loginView){
        this.activity = activity;
        this.loginView = loginView;
        loginModelImple = new LoginModelImple();
    }

    public LoginPresent(){
        loginModelImple = new LoginModelImple();
    }

    public void login() {
        loginModelImple.login(loginView.getPhoneNum(),loginView.getPwd(),this);
    }

    public void changeEdittextPwdStatus(EditText etPassword, ImageView ivEyePwd) {
        loginModelImple.changeEdittextPwdStatus(etPassword,ivEyePwd);
    }

    @Override
    public void onSuccess(BaseRequestBean bean) {
//        SPUtils.getInstance(BaseApp.getContext()).putString(IConstant.MOBILE, phoneNum);
        SPUtils.getInstance(BaseApp.getContext()).putBoolean(IConstant.IS_LOGIN, true);
        ToastUtils.show(bean.info);
        if(loginView.isCheckLogin()){
            loginView.moveToAccountInfoActivity();
        }
        loginView.finishActivity();
    }

    @Override
    public void onFailed(String errorInfo) {
        ToastUtils.show(errorInfo);
    }

    public void loginWX() {
        loginModelImple.loginWX(activity);
    }
}
