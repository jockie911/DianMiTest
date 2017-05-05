package com.example.objLoader.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.global.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailClothesModelFragment extends BaseFragment {

    @Bind(R.id.iv_top_clothes)
    ImageView ivTopClothes;
    @Bind(R.id.tv_top_measure_size)
    TextView tvTopMeasureSize;
    @Bind(R.id.iv_bottom_clothes)
    ImageView ivBottomClothes;
    @Bind(R.id.tv_bottom_measure_size)
    TextView tvBottomMeasureSize;
    @Bind(R.id.tv_shape)
    TextView tvShape;

    @Override
    protected View inflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_detail_clothes_model, null);
    }

    @Override
    protected void initData() {

    }
}
