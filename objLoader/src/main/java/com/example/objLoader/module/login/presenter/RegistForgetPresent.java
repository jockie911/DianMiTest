package com.example.objLoader.module.login.presenter;

import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.module.login.imple.OnSmsCheckoutListener;
import com.example.objLoader.module.login.imple.OnSmsCodeSubmitListerer;
import com.example.objLoader.module.login.imple.OnSubmitRequestListener;
import com.example.objLoader.module.login.imple.IRegistForgetView;
import com.example.objLoader.module.login.imple.RegistForgetModelImple;
import com.example.objLoader.utils.ToastUtils;

/**
 * Created by yc on 2017/5/22.
 */

public class RegistForgetPresent extends BasePresenter<IRegistForgetView> implements OnSmsCodeSubmitListerer,OnSubmitRequestListener,OnSmsCheckoutListener{

    private RegistForgetModelImple registForgeftModelImple;

    public void requestSmsCode(TextView tv_send_auth_code) {
        registForgeftModelImple.setTextView(tv_send_auth_code);
        registForgeftModelImple.requestSmsCode(mBaseView.getPhoneNum(),this);
    }

    public void registForgetSubmit(){
        registForgeftModelImple.checkOutSmsCode(mBaseView.getPhoneNum(),mBaseView.getSmsCode(),this);
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
        registForgeftModelImple.submitRequest(mBaseView.getPhoneNum(),mBaseView.getSmsCode(),
                mBaseView.getPws(),mBaseView.isForgetPwd(),this);
    }

    @Override
    public void onCheckoutFalied() {

    }

    @Override
    public void onSubmitSuccess(BaseRequestBean bean) {
        ToastUtils.show(bean.info);
        mBaseView.finishActivity();
    }

    @Override
    public void onSubmitFailed(String errorInfo) {
        ToastUtils.show(errorInfo);
    }
}
