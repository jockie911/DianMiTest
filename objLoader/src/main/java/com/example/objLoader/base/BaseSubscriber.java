package com.example.objLoader.base;

import android.app.Activity;
import android.text.TextUtils;

import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.wedgit.LoadingDialog;

import rx.Subscriber;

/**
 * Created by yc on 2017/6/2.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private final Activity activity;
    private LoadingDialog loadingDialog;

    public BaseSubscriber(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCompleted() {
        dismiss();
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e.getMessage());
        dismiss();
    }

    @Override
    public void onNext(T o) {
        if(o instanceof BaseRequestBean){
            BaseRequestBean o1 = (BaseRequestBean) o;
            if(TextUtils.equals("0",o1.iserror)){
                 onSuccess(o);
            }else{
                onFailed(o1.info);
            }
        }
        dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(activity != null){
            loadingDialog = new LoadingDialog(activity);
            loadingDialog.builder().show();
        }
    }

    private void dismiss() {
        if(loadingDialog != null)
        loadingDialog.dismiss();
    }

    public abstract void onSuccess(T t);

    public abstract void onFailed(String errMsg);
}
