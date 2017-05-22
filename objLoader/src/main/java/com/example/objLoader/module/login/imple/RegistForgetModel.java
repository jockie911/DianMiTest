package com.example.objLoader.module.login.imple;

/**
 * Created by yc on 2017/5/22.
 */

public interface RegistForgetModel {

    void requestSmsCode(String phoneNum,OnSmsCodeSubmitListerer listerer);

    void checkOutSmsCode(String phoneNUm,String smsCode,OnSmsCheckoutListener listener);

    void submitRequest(String phoneNum,String smsCode,String pwd,boolean isForgetPws,OnSubmitRequestListener listener);

}
