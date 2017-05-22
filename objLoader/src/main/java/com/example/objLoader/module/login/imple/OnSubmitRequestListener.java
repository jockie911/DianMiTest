package com.example.objLoader.module.login.imple;

import com.example.objLoader.bean.BaseRequestBean;

/**
 * Created by yc on 2017/5/22.
 */

public interface OnSubmitRequestListener {

    void onSubmitSuccess(BaseRequestBean bean);

    void onSubmitFailed(String errorInfo);
}
