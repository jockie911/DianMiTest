package com.example.objLoader.present.listener;

import com.example.objLoader.bean.BaseRequestBean;

/**
 * Created by yc on 2017/5/22.
 */

public interface OnLoginListener {

    void onSuccess(BaseRequestBean bean);

    void onFailed(String errorInfo);
}
