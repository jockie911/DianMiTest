package com.example.objLoader.module.login.imple;

/**
 * Created by yc on 2017/5/22.
 */

public interface LoginModel {
    void login(String phoneNum,String pws,OnLoginListener listener);
}
