package com.example.objLoader.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.activity.DetailMeasureSizeActivity;
import com.example.objLoader.global.BaseFragment;
import com.example.objLoader.istatic.IConstant;

import butterknife.Bind;

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
        if(getActivity() != null){
            int gender = ((DetailMeasureSizeActivity) getActivity()).getGender();

            if(gender == IConstant.GENDER_FEMALE){
                ivTopClothes.setImageResource(R.drawable.top_clothes_woman);
                ivBottomClothes.setImageResource(R.drawable.bottom_clothes_woman);
            }else if(gender == IConstant.GENDEER_MALE){
                ivTopClothes.setImageResource(R.drawable.top_clothes_man);
                ivBottomClothes.setImageResource(R.drawable.bottom_clothes_man);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}
