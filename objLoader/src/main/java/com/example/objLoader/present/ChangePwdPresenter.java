package com.example.objLoader.present;

import android.text.TextUtils;

import com.example.objLoader.R;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.net.RestClient;
import com.example.objLoader.present.view.ChangePwdView;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.utils.Utils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yc on 2017/5/25.
 */

public class ChangePwdPresenter extends BasePresenter<ChangePwdView>{

    public void changechangePwd() {
        String oldPwd = mBaseView.getOldPwd();
        String newPwd = mBaseView.getNewPwd();
        if(oldPwd.length() < 5 || newPwd.length() < 5){
            ToastUtils.show(R.string.pwd_lenght);
            return;
        }
        if(TextUtils.equals(oldPwd,newPwd)){
            ToastUtils.show(R.string.twice_same_psw);
            return;
        }

        String mobile = SPUtils.getInstance().getString(IConstant.MOBILE);
        RestClient.instance().changeUserPwd(mobile,Utils.MD5(oldPwd),Utils.MD5(newPwd), Utils.MD5(Utils.MD5(newPwd) +
                Utils.MD5(mobile + Constants.MD5_KEY + Utils.MD5(oldPwd))))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<BaseRequestBean>() {
            @Override
            public void call(BaseRequestBean baseRequestBean) {
                if(TextUtils.equals("0",baseRequestBean.iserror)){
                    mBaseView.onChangePwsSuccess();
                }else{
                    mBaseView.onChangePwdError(baseRequestBean.info);
                }
            }
        });
    }
}
