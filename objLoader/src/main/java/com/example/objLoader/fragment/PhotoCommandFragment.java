package com.example.objLoader.fragment;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.example.objLoader.R;
import com.example.objLoader.activity.FrontPicActivity;
import com.example.objLoader.activity.SidePicActivity;
import com.example.objLoader.istatic.IConstant;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yc on 2017/5/9.
 */

@SuppressLint("ValidFragment")
public class PhotoCommandFragment extends com.example.objLoader.global.BaseFragment {

    private String frontOrSide;

    @SuppressLint("ValidFragment")
    public PhotoCommandFragment(String frontOrSide) {
        this.frontOrSide = frontOrSide;
    }

    @Override
    protected View inflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_photo_command,null);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_close})
    @Override
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
        switch (v.getId()){
            case R.id.iv_close:
                if(getActivity() != null){
                    if(isFromFront()){
                        ((FrontPicActivity)getActivity()).initShowFragment(false);
                    }else{
//                        ((SidePicActivity)getActivity()).initShowFragment(false);
                    }
                }
                break;
        }
    }

    /**
     * true 正面照| false 侧面照
     * @return
     */
    private boolean isFromFront(){
        return TextUtils.equals(frontOrSide, IConstant.FRONT_PIC_PATH);
    }
}
