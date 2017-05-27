package com.example.objLoader.module;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.objLoader.R;
import com.example.objLoader.adapter.RecordMeasureAdapter;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.utils.Logger;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.Utils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MeasureRecordActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.lv_record_measure)
    ListView lvRecordMeasure;
    @Bind(R.id.rel_bottom)
    RelativeLayout relBottom;

    private RecordMeasureAdapter recordMeasureAdapter;
    private boolean isEdit; //是否处于编辑状态

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
        String mobile = SPUtils.getInstance().getString(IConstant.MOBILE);

        stringRequest.add(IConstant.MOBILE, mobile);
        stringRequest.add(IConstant.STRING, Utils.MD5(mobile + Constants.MD5_KEY + mobile));
        CallServer.getInstance().add(this,stringRequest,callBack,1,true,true,MeasureRecordBean.class);


        recordMeasureAdapter = new RecordMeasureAdapter(MeasureRecordActivity.this, null, R.layout.item_lv_record_measure);
        lvRecordMeasure.setAdapter(recordMeasureAdapter);
        lvRecordMeasure.setOnItemClickListener(this);
    }

    private HttpCallBack<MeasureRecordBean> callBack = new HttpCallBack<MeasureRecordBean>() {

        @Override
        public void onSucceed(int what, MeasureRecordBean bean) {
            recordMeasureAdapter.addData(bean.getData());
        }
    };

    @OnClick({R.id.tv_right_title_bar,R.id.tv_delete_record})
    @Override
    public void onClick(View v) {
        if (isDoubleClick(v)) return;
        switch (v.getId()){
            case R.id.tv_right_title_bar:
                isEdit = !isEdit;
                recordMeasureAdapter.setCheckBoxVisblity(isEdit);
                if(isEdit){
                    tvRightTitle.setText(R.string.cancel);
                    relBottom.setVisibility(View.VISIBLE);
                    //TODO  show dialog
                }else{
                    tvRightTitle.setText(R.string.editor);
                    relBottom.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.tv_delete_record:
                List<String> deletePosition = recordMeasureAdapter.getDeletePosition();

                //TODO connect to net to delete
                Logger.d(deletePosition.toString());
                recordMeasureAdapter.deleteSuccessRefreshData();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(isEdit) return; // 编辑状态不可以点击

        MeasureRecordBean.DataBean itemData = recordMeasureAdapter.getItem(position);

        Intent intent = new Intent(MeasureRecordActivity.this, DetailMeasureSizeActivity.class);
        intent.putExtra(IConstant.ITEM_DATA,itemData);
        startActivity(intent);
    }
}
