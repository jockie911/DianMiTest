package com.example.objLoader.base;

import android.content.Context;

import com.example.objLoader.rx.RxBus;
import com.example.objLoader.rx.RxEvent;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yc on 2017/5/31.
 */

public abstract class BasePresenter<T extends IBaseView> {

    protected T mBaseView;
    protected CompositeSubscription mCompositeSubscription;

    public void attachView(T mBaseView) {
        this.mBaseView = mBaseView;
        onViewAttach();
        mCompositeSubscription = new CompositeSubscription();
        initSubscription();
    }

    protected void onViewAttach() {
    }

    protected void initSubscription() {

    }

    protected void addSubscription(@RxEvent.EventType int type, Action1<RxEvent> action) {
        mCompositeSubscription.add(RxBus.getInstance().toObservable(type).subscribe(action));
    }

    public void detachView() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
        mBaseView = null;
    }

    public Context getApplication() {
        return BaseApp.getContext();
    }

}
