package com.example.objLoader.module.login.imple;

/**
 * Created by yc on 2017/5/22.
 */

public interface RegistForgetView {

    String getPhoneNum();

    String getSmsCode();

    String getPws();

    void finishActivity();

    boolean isForgetPwd();
}
