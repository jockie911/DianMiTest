package com.example.objLoader.module.login.presenter;

import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.module.login.imple.OnSmsCheckoutListener;
import com.example.objLoader.module.login.imple.OnSmsCodeSubmitListerer;
import com.example.objLoader.module.login.imple.OnSubmitRequestListener;
import com.example.objLoader.module.login.imple.RegistForgetView;
import com.example.objLoader.module.login.imple.RegistForgetModelImple;
import com.example.objLoader.utils.ToastUtils;

/**
 * Created by yc on 2017/5/22.
 */

public class RegistForgetPresent implements OnSmsCodeSubmitListerer,OnSubmitRequestListener,OnSmsCheckoutListener{

    private RegistForgetModelImple registForgeftModelImple;
    private RegistForgetView registForgetView;

    public RegistForgetPresent(RegistForgetView registForgetView){
        this.registForgetView = registForgetView;
        registForgeftModelImple = new RegistForgetModelImple();
    }

    public void requestSmsCode(TextView tv_send_auth_code) {
        registForgeftModelImple.setTextView(tv_send_auth_code);
        registForgeftModelImple.requestSmsCode(registForgetView.getPhoneNum(),this);
    }

    public void registForgetSubmit(){
        registForgeftModelImple.checkOutSmsCode(registForgetView.getPhoneNum(),registForgetView.getSmsCode(),this);
    }

    public void cancel() {
        registForgeftModelImple.cancel();
    }

    @Override
    public void onSmsCodeSuccess() {
        ToastUtils.show(R.string.auth_code_already);
    }

    @Override
    public void onSmsCodeFailed() {

    }

    @Override
    public void onCheckoutSuccess() {
        registForgeftModelImple.submitRequest(registForgetView.getPhoneNum(),registForgetView.getSmsCode(),
                registForgetView.getPws(),registForgetView.isForgetPwd(),this);
    }

    @Override
    public void onCheckoutFalied() {

    }

    @Override
    public void onSubmitSuccess(BaseRequestBean bean) {
        ToastUtils.show(bean.info);
        registForgetView.finishActivity();
    }

    @Override
    public void onSubmitFailed(String errorInfo) {
        ToastUtils.show(errorInfo);
    }
}
