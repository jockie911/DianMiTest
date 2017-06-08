package com.example.objLoader.present.view;

import com.example.objLoader.base.IBaseView;

/**
 * Created by yc on 2017/5/22.
 */

public interface IRegistForgetView extends IBaseView {

    String getPhoneNum();

    String getSmsCode();

    String getPws();

    void finishActivity();

    boolean isForgetPwd();

    boolean isBindMobile();
}
