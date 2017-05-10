package com.example.objLoader.fragment;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.activity.FrontPicActivity;
import com.example.objLoader.activity.SidePicActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.wedgit.CameraSurfaceView;
import com.example.objLoader.wedgit.camera.CameraManager;
import com.example.objLoader.wedgit.camera.SquareCameraContainer;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yc on 2017/5/9.
 */

@SuppressLint("ValidFragment")
public class PhotoCommandFragment extends com.example.objLoader.global.BaseFragment {

    @Bind(R.id.iv_take_photo)
    ImageView ivTakePhoto;
//    @Bind(R.id.camera_surfaceview)
//    CameraSurfaceView cameraSurfaceView;
    @Bind(R.id.cameraContainer)
    SquareCameraContainer mCameraContainer;

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
        mCameraContainer.bindActivity((FrontPicActivity) getActivity());

        CameraManager mCameraManager = CameraManager.getInstance(getActivity());

//        mCameraManager.bindOptionMenuView();
    }

    @OnClick({R.id.iv_close,R.id.iv_take_photo})
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
            case R.id.iv_take_photo:
//                cameraSurfaceView.takePhoto();
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
