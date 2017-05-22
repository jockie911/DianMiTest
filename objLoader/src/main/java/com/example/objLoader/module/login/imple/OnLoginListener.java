package com.example.objLoader.module.login.imple;

import com.example.objLoader.bean.BaseRequestBean;

/**
 * Created by yc on 2017/5/22.
 */

public interface OnLoginListener {

    void onSuccess(BaseRequestBean bean);

    void onFailed(String errorInfo);
}
