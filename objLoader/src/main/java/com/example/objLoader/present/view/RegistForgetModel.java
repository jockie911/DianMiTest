package com.example.objLoader.present.view;

import com.example.objLoader.present.listener.OnSmsCheckoutListener;
import com.example.objLoader.present.listener.OnSmsCodeSubmitListerer;
import com.example.objLoader.present.listener.OnSubmitRequestListener;

/**
 * Created by yc on 2017/5/22.
 */

public interface RegistForgetModel {

    void requestSmsCode(String phoneNum,OnSmsCodeSubmitListerer listerer);

    void checkOutSmsCode(String phoneNUm,String smsCode,OnSmsCheckoutListener listener);

    void submitRequest(String phoneNum,String smsCode,String pwd,boolean isForgetPws,OnSubmitRequestListener listener);

}
