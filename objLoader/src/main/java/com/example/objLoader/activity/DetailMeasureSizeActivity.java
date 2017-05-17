package com.example.objLoader.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.adapter.VpTableAdapter;
import com.example.objLoader.bean.MeasureRecordBean;
import com.example.objLoader.fragment.DetailClothesModelFragment;
import com.example.objLoader.fragment.DetailsSizeFragment;
import com.example.objLoader.fragment.ModelFragment;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.utils.AlertEtDialog;
import com.example.objLoader.utils.LoginUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class DetailMeasureSizeActivity extends BaseActivity {

    @Bind(R.id.tablayout)
    protected TabLayout tableLayout;
    @Bind(R.id.vp)
    protected ViewPager vpLove;
    @Bind(R.id.tv_save_record)
    TextView tvSaveRecord;

    protected List<String> tabTitleLists;
    protected List<Fragment> fmLists;
    private MeasureRecordBean.DataBean itemdata;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail_measure_size;
    }

    @Override
    protected void initData() {
        boolean issave = getIntent().getBooleanExtra(IConstant.IS_SAVE, false);
        tvSaveRecord.setVisibility(issave ? View.VISIBLE : View.GONE);

        itemdata = getIntent().getParcelableExtra(IConstant.ITEM_DATA);


        tabTitleLists = new ArrayList<>();
        tabTitleLists.add("详细尺寸");
        tabTitleLists.add("号型匹配");
        tabTitleLists.add("3D模型");

        fmLists = new ArrayList<>();
        fmLists.add(new DetailsSizeFragment());
        fmLists.add(new DetailClothesModelFragment());
        fmLists.add(new ModelFragment());

        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        VpTableAdapter vpTableAdapter = new VpTableAdapter(getSupportFragmentManager(),fmLists,tabTitleLists,tableLayout);
        vpLove.setAdapter(vpTableAdapter);
        tableLayout.setupWithViewPager(vpLove);
    }

    @OnClick({R.id.tv_save_record})
    @Override
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
        if(v.getId() == R.id.tv_save_record){
            showSavaRecordDialog();
        }
    }

    public List<MeasureRecordBean.DataBean.InfoBean> getDetailInfo(){
        if(itemdata == null) return null;
        return this.itemdata.getInfo();
    }

    public String get3DTreeUrl() {
        if(itemdata == null) return "";
        return itemdata.getThreeshowurl();
    }

    public int getGender(){
        if(itemdata != null && itemdata.getInfo() != null){
            String value = itemdata.getInfo().get(itemdata.getInfo().size() - 1).getValue();
            if(TextUtils.equals("女",value)){
                return IConstant.GENDER_FEMALE;
            }else if(TextUtils.equals("男",value)){
                return IConstant.GENDEER_MALE;
            }
        }
        return IConstant.ZERO;
    }
    /**
     * save current record
     */
    private void showSavaRecordDialog() {
        LoginUtils.isLogin();

        final AlertEtDialog builder = new AlertEtDialog(DetailMeasureSizeActivity.this).builder();
        builder.setTitle(R.string.sava_record_name_hint);
        builder.setNegativeButton(R.string.cancel,null);
        builder.setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.getEtCentent();
                Log.d("TAG",builder.getEtCentent());
            }
        }).show();
    }


}
