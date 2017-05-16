package com.example.objLoader.fragment;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Bind(R.id.cameraContainer)
    SquareCameraContainer mCameraContainer;
    @Bind(R.id.tv_flashlight)
    TextView mFlashLight;
    @Bind(R.id.ib_take_photo)
    ImageButton mTakePhoto;

    private String frontOrSide;
    private CameraManager mCameraManager;

    @SuppressLint("ValidFragment")
    public PhotoCommandFragment(String frontOrSide) {
        this.frontOrSide = frontOrSide;
    }

    @Override
    protected View inflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_photo_command,null,true);
    }

    @Override
    protected void initData() {
        mCameraContainer.bindActivity((FrontPicActivity) getActivity());

        mCameraManager = CameraManager.getInstance(getActivity());

        mCameraManager.bindOptionMenuView(mFlashLight,null);

        mCameraContainer.bindActivity((FrontPicActivity) getActivity());
    }

    @OnClick({R.id.ib_close,R.id.ib_take_photo,R.id.tv_flashlight})
    @Override
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
        switch (v.getId()){
            case R.id.ib_close:
                if(getActivity() != null){
                    if(isFromFront()){
                        ((FrontPicActivity)getActivity()).initShowFragment(false);
                    }else{
//                        ((SidePicActivity)getActivity()).initShowFragment(false);
                    }
                }
                break;
            case R.id.ib_take_photo:
//                cameraSurfaceView.takePhoto();
                mCameraContainer.takePicture();

//                ((FrontPicActivity)getActivity()).setCurrentBitmp();
                break;
            case R.id.tv_flashlight:
                mCameraContainer.switchFlashMode();
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

    @Override
    public void onStart() {
        super.onStart();
        if(mCameraContainer != null)
            mCameraContainer.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mCameraContainer != null)
            mCameraContainer.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            if(mCameraContainer != null)
                mCameraContainer.onStop();
        }else{
            if(mCameraContainer != null)
                mCameraContainer.onStart();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mCameraManager != null){
            mCameraManager.unbinding();
            mCameraManager.releaseActivityCamera();
        }
    }
}
