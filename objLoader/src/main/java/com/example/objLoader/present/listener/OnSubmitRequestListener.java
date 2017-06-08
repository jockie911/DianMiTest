package com.example.objLoader.present.listener;

import com.example.objLoader.bean.BaseRequestBean;

/**
 * Created by yc on 2017/5/22.
 */

public interface OnSubmitRequestListener {

    void onSubmitSuccess(BaseRequestBean bean);

    void onSubmitFailed(String errorInfo);
}
