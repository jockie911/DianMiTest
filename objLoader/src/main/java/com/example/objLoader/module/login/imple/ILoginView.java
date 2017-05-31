package com.example.objLoader.module.login.imple;

import com.example.objLoader.base.IBaseView;

/**
 * Created by yc on 2017/5/22.
 */

public interface ILoginView extends IBaseView{

    String getPhoneNum();

    String getPwd();

    void moveToAccountInfoActivity();

    boolean isCheckLogin();

    void finishActivity();

}
