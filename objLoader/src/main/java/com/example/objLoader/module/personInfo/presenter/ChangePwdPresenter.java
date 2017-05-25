package com.example.objLoader.module.personInfo.presenter;

import android.text.TextUtils;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.net.RestClient;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.utils.Utils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yc on 2017/5/25.
 */

public class ChangePwdPresenter {

    private ChangePwdView changePwdView;

    public ChangePwdPresenter(ChangePwdView changePwdView) {
        this.changePwdView = changePwdView;
    }

    public void changechangePwd() {
        String oldPwd = changePwdView.getOldPwd();
        String newPwd = changePwdView.getNewPwd();
        if(oldPwd.length() < 5 || newPwd.length() < 5){
            ToastUtils.show(R.string.pwd_lenght);
            return;
        }
        String mobile = SPUtils.getInstance(BaseApp.getContext()).getString(IConstant.MOBILE);
        RestClient.instance().changeUserPwd(mobile,Utils.MD5(oldPwd),Utils.MD5(newPwd), Utils.MD5(Utils.MD5(newPwd) +
                Utils.MD5(mobile + Constants.MD5_KEY + Utils.MD5(oldPwd))))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<BaseRequestBean>() {
            @Override
            public void call(BaseRequestBean baseRequestBean) {
                if(TextUtils.equals("0",baseRequestBean.iserror)){
                    changePwdView.onChangePwsSuccess();
                }else{
                    changePwdView.onChangePwdError(baseRequestBean.info);
                }
            }
        });
    }
}
