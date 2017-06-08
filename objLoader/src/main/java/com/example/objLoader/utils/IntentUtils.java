package com.example.objLoader.utils;

import android.content.Context;
import android.content.Intent;

import com.example.objLoader.R;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.DetailMeasureSizeActivity;
import com.example.objLoader.module.MeasureRecordActivity;
import com.example.objLoader.module.WebviewActivity;
import com.example.objLoader.module.login.BindMobileActivity;

/**
 * Created by yc on 2017/6/6.
 */

public class IntentUtils {

    private static int[] webviewTitleDatas= {R.string.user_agreement};
    private static String[] webviewUrlDatas= {""};

    public static void startToWebview(Context context,int dataPosition){
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra(IConstant.TITLE,context.getResources().getString(webviewTitleDatas[dataPosition]));
        intent.putExtra(IConstant.URL,webviewUrlDatas[dataPosition]);
        context.startActivity(intent);
    }

    public static void startToDetailMeasureSize(Context context,MeasureRecordBean.DataBean item) {
        Intent intent = new Intent(context, DetailMeasureSizeActivity.class);
        intent.putExtra(IConstant.ITEM_DATA,item);
        context.startActivity(intent);
    }

    public static void startToBindMobile(Context context, String code) {
        Intent intent = new Intent(context, BindMobileActivity.class);
        intent.putExtra(IConstant.CODE,code);
        context.startActivity(intent);
    }
}
