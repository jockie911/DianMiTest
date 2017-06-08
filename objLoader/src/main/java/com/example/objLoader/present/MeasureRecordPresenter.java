package com.example.objLoader.present;

import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.base.BaseSubscriber;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.present.view.IMeasureRecordView;
import com.example.objLoader.net.RestClient;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yc on 2017/6/8.
 */

public class MeasureRecordPresenter<T extends BaseActivity> extends BasePresenter<IMeasureRecordView> {

    private WeakReference<T> mActivity;

    public MeasureRecordPresenter(T t) {
        mActivity = new WeakReference<T>(t);
    }

    public void getRecordDateFromNet(){
        String mobile = SPUtils.getInstance().getString(IConstant.MOBILE);
//        CallServer.getInstance().add(this,stringRequest,callBack,1,true,true,MeasureRecordBean.class);
        RestClient.instance().getRecordMeasureData(Constants.GET_MEASURE_RECORD,mobile,Utils.MD5(mobile + Constants.MD5_KEY + mobile))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MeasureRecordBean>(mActivity.get()) {
                    @Override
                    public void onSuccess(MeasureRecordBean o) {
                        mBaseView.setRecordData(o.getData());
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        ToastUtils.show(errMsg);
                    }
                });
    }

    public void deteleSelectedRecoedLists() {
        List<String> deleteRecordList = mBaseView.getDeleteRecordList();
        String mobile = SPUtils.getInstance().getString(IConstant.MOBILE);
        String recid = "";
        if(deleteRecordList == null || deleteRecordList.size() == 0){
            return;
        }else if(deleteRecordList.size() == 1){
            recid = deleteRecordList.get(0);
        }else{
            for (int i = 0; i < deleteRecordList.size(); i++) {
                if(i != deleteRecordList.size()-1){
                    recid = recid + deleteRecordList.get(i) + ",";
                }else{
                    recid = recid + deleteRecordList.get(i);
                }
            }
        }
        RestClient.instance().deleteMyMeasureRecord(Constants.DELETE_MY_LOG,mobile,recid, Utils.MD5(mobile + Constants.MD5_KEY + recid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseRequestBean>(mActivity.get()) {
                    @Override
                    public void onSuccess(BaseRequestBean o) {
                        mBaseView.deleteSuccessRefreshData();
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        ToastUtils.show(errMsg);
                    }
                });
    }
}
