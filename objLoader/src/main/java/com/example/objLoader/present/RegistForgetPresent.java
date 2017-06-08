package com.example.objLoader.present;

import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.module.login.BindMobileActivity;
import com.example.objLoader.present.listener.OnSmsCheckoutListener;
import com.example.objLoader.present.listener.OnSmsCodeSubmitListerer;
import com.example.objLoader.present.listener.OnSubmitRequestListener;
import com.example.objLoader.present.view.IRegistForgetView;
import com.example.objLoader.utils.ToastUtils;

/**
 * Created by yc on 2017/5/22.
 */

public class RegistForgetPresent<T extends BaseActivity> extends BasePresenter<IRegistForgetView> implements OnSmsCodeSubmitListerer,OnSubmitRequestListener,OnSmsCheckoutListener,RegistForgetModelImple.IBindMobile {

    private T mActivity;
    private RegistForgetModelImple registForgeftModelImple;

    public RegistForgetPresent(){}

    public RegistForgetPresent(T mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected void onViewAttach() {
        registForgeftModelImple = new RegistForgetModelImple();
    }

    public void requestSmsCode(TextView tv_send_auth_code) {
        registForgeftModelImple.setTextView(tv_send_auth_code);
        registForgeftModelImple.requestSmsCode(mBaseView.getPhoneNum(),this);
    }

    public void registForgetSubmit(){
        registForgeftModelImple.checkOutSmsCode(mBaseView.getPhoneNum(),mBaseView.getSmsCode(),this);
    }

    public void bindMobile() {
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
        if(mBaseView.isBindMobile()){
            registForgeftModelImple.bindMobile(mActivity,mBaseView.getPws(),mBaseView.getPhoneNum(),this);
        }else{
            registForgeftModelImple.submitRequest(mBaseView.getPhoneNum(),mBaseView.getSmsCode(),
                    mBaseView.getPws(),mBaseView.isForgetPwd(),this);
        }
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

    /**
     * 绑定手机号成功
     */
    @Override
    public void onBindMobileSuccess() {
        mBaseView.finishActivity();
    }
}
