package com.example.objLoader.module.personInfo.presenter;

import com.example.objLoader.base.IBaseView;

/**
 * Created by yc on 2017/5/25.
 */

public interface ChangePwdView extends IBaseView{

    String getOldPwd();

    String getNewPwd();

    void onChangePwsSuccess();

    void onChangePwdError(String errorInfo);

}