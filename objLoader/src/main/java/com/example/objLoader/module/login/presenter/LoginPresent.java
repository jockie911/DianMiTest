package com.example.objLoader.module.login.presenter;

import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.login.LoginActivity;
import com.example.objLoader.module.login.imple.ILoginView;
import com.example.objLoader.module.login.imple.LoginModelImple;
import com.example.objLoader.module.login.imple.OnLoginListener;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;

/**
 * Created by yc on 2017/5/22.
 */

public class LoginPresent<T extends BaseActivity> extends BasePresenter<ILoginView> implements OnLoginListener{

    private T activity;
    private LoginModelImple loginModelImple;

    public LoginPresent(T activity){
        this.activity = activity;
        loginModelImple = new LoginModelImple();
    }

    public LoginPresent(){
        loginModelImple = new LoginModelImple();
    }

    public void login() {
        loginModelImple.login(mBaseView.getPhoneNum(),mBaseView.getPwd(),this);
    }

    public void changeEdittextPwdStatus(EditText etPassword, ImageView ivEyePwd) {
        loginModelImple.changeEdittextPwdStatus(etPassword,ivEyePwd);
        
    }

    @Override
    public void onSuccess(BaseRequestBean bean) {
//        SPUtils.getInstance(BaseApp.getContext()).putString(IConstant.MOBILE, phoneNum);
        SPUtils.getInstance().putBoolean(IConstant.IS_LOGIN, true);
        ToastUtils.show(bean.info);
        if(mBaseView.isCheckLogin()){
            mBaseView.moveToAccountInfoActivity();
        }
        mBaseView.finishActivity();
    }

    @Override
    public void onFailed(String errorInfo) {
        ToastUtils.show(errorInfo);
    }

    public void loginWX() {
        loginModelImple.loginWX(activity);
    }

    public static boolean isLogin(Boolean... isCheckLogin){
        boolean aBoolean = SPUtils.getInstance().getBoolean(IConstant.IS_LOGIN);
        if(!aBoolean){
            Intent intent = new Intent(BaseApp.getContext(), LoginActivity.class);
            if(isCheckLogin.length > 0)
                intent.putExtra(IConstant.IS_CHECK_LOGIN,!(isCheckLogin.length > 0 && !isCheckLogin[0]));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApp.getContext().startActivity(intent);
            return false;
        }
        return true;
    }
}
