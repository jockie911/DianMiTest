package com.example.objLoader.module.login.imple;

/**
 * Created by yc on 2017/5/22.
 */

public interface LoginView {

    String getPhoneNum();

    String getPwd();

    void moveToAccountInfoActivity();

    boolean isCheckLogin();

    void finishActivity();

}
