package com.example.objLoader.activity.mywork;

import android.util.Log;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.bean.MeasureInfo;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.Constants;
import com.example.objLoader.utils.GsonTools;
import com.example.objLoader.utils.JLog;
import com.example.objLoader.utils.LoginUtils;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Utils;
import com.google.gson.Gson;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

public class MeasureRecordActivity extends BaseActivity {


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_measure_record;
    }

    @Override
    protected boolean isHavaBaseTitleBar() {
        return true;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.account_info_measure_record);
        tvRightTitle.setText(R.string.editor);
        tvRightTitle.setTextColor(getResources().getColor(R.color.yollow));

        Request<String> stringRequest = NoHttp.createStringRequest(Constants.GET_MEASURE_RECORD, RequestMethod.POST);
        String mobile = SharedPreferencesDAO.getInstance(this).getString(IConstant.MOBILE);

        JLog.d(mobile + "  moible");
        stringRequest.add(IConstant.MOBILE, mobile);
        stringRequest.add(IConstant.STRING, Utils.MD5(mobile + Constants.MD5_KEY + mobile));
        CallServer.getInstance().add(this,stringRequest,callBack,1,true,true,MeasureRecordBean.class);
    }

    private HttpCallBack<MeasureRecordBean> callBack = new HttpCallBack<MeasureRecordBean>() {

        @Override
        public void onSucceed(int what, MeasureRecordBean bean) {

            JLog.d(bean.getData().size() + "  [[[[[ ]]]]] ");
        }
    };

}
