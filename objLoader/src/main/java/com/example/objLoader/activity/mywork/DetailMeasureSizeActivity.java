package com.example.objLoader.activity.mywork;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.adapter.VpTableAdapter;
import com.example.objLoader.fragment.DetailClothesModelFragment;
import com.example.objLoader.fragment.DetailsSizeFragment;
import com.example.objLoader.fragment.ModelFragment;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class DetailMeasureSizeActivity extends BaseActivity {

    @Bind(R.id.tablayout)
    protected TabLayout tableLayout;
    @Bind(R.id.vp)
    protected ViewPager vpLove;
    @Bind(R.id.tv_save_record)
    TextView tvSaveRecord;

    protected List<String> tabTitleLists;
    protected List<Fragment> fmLists;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail_measure_size;
    }

    @Override
    protected void initData() {
        boolean issave = getIntent().getBooleanExtra(IConstant.IS_SAVE, false);
        tvSaveRecord.setVisibility(issave ? View.VISIBLE : View.GONE);

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

    @Override
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
    }
}
