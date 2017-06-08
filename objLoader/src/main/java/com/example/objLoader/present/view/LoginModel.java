package com.example.objLoader.present.view;

import com.example.objLoader.present.listener.OnLoginListener;

/**
 * Created by yc on 2017/5/22.
 */

public interface LoginModel {

    void login(String phoneNum,String pws,OnLoginListener listener);
}
