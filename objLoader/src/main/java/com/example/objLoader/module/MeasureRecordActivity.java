package com.example.objLoader.module;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.objLoader.R;
import com.example.objLoader.adapter.RecordMeasureAdapter;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.present.view.IMeasureRecordView;
import com.example.objLoader.present.MeasureRecordPresenter;
import com.example.objLoader.utils.IntentUtils;
import com.example.objLoader.utils.MySnackbar;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MeasureRecordActivity extends BaseActivity implements AdapterView.OnItemClickListener,IMeasureRecordView {

    @Bind(R.id.lv_record_measure)
    ListView lvRecordMeasure;
    @Bind(R.id.rel_bottom)
    RelativeLayout relBottom;
    private MeasureRecordPresenter mPresenter;
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

        recordMeasureAdapter = new RecordMeasureAdapter(this, null, R.layout.item_lv_record_measure);
        lvRecordMeasure.setAdapter(recordMeasureAdapter);
        lvRecordMeasure.setOnItemClickListener(this);

        mPresenter.getRecordDateFromNet();
    }

    @Override
    protected BasePresenter initPresenter() {
        mPresenter = new MeasureRecordPresenter(this);
        mPresenter.attachView(this);
        return mPresenter;
    }

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
                }else{
                    tvRightTitle.setText(R.string.editor);
                    relBottom.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.tv_delete_record:
                mPresenter.deteleSelectedRecoedLists();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(isEdit) return; // 编辑状态不可以点击
        IntentUtils.startToDetailMeasureSize(this,recordMeasureAdapter.getItem(position));
    }

    @Override
    public void setRecordData(List<MeasureRecordBean.DataBean> data) {
        recordMeasureAdapter.addData(data);
    }

    @Override
    public List<String> getDeleteRecordList() {
        return recordMeasureAdapter.getDeletePosition();
    }

    @Override
    public void deleteSuccessRefreshData() {
        MySnackbar.makeSnackBarGreen(tvTitle,getResources().getString(R.string.delete_record_success));
        recordMeasureAdapter.deleteSuccessRefreshData();
    }
}
