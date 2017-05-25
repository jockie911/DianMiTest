package com.example.objLoader.module.personInfo.presenter;

/**
 * Created by yc on 2017/5/25.
 */

public interface ChangePwdView {

    String getOldPwd();

    String getNewPwd();

    void onChangePwsSuccess();

    void onChangePwdError(String errorInfo);

}
