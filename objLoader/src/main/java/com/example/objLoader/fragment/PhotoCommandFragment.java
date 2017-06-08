package com.example.objLoader.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.module.measure.FrontPicActivity;
import com.example.objLoader.module.measure.SidePicActivity;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.wedgit.ShapeView;
import com.example.objLoader.wedgit.camera.CameraManager;
import com.example.objLoader.wedgit.camera.SquareCameraContainer;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yc on 2017/5/9.
 * frontPic and sidePic with photo use this fragment,only gender and frontOrSide controll all
 */

@SuppressLint("ValidFragment")
public class PhotoCommandFragment extends com.example.objLoader.base.BaseFragment {

    @Bind(R.id.cameraContainer)
    SquareCameraContainer mCameraContainer;
    @Bind(R.id.tv_flashlight)
    TextView mFlashLight;
    @Bind(R.id.ib_take_photo)
    ImageButton mTakePhoto;
    @Bind(R.id.shapeview)
    ShapeView shapeView;

    private int gender;
    private boolean isFrontTakePhoto;
    private CameraManager mCameraManager;

    /**
     * @param isFrontTakePhoto is photo front
     * @param gender
     */
    @SuppressLint("ValidFragment")
    public PhotoCommandFragment(boolean isFrontTakePhoto,int gender) {
        this.isFrontTakePhoto = isFrontTakePhoto;
        this.gender = gender;
    }

    @Override
    protected View inflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_photo_command,null,true);
    }

    @Override
    protected void initData() {
        shapeView.setVisibility(isFrontTakePhoto?View.VISIBLE : View.GONE);
        shapeView.setIsMale(gender == IConstant.GENDEER_MALE);

        mCameraContainer.bindActivity((BaseActivity) getActivity(),isFrontTakePhoto,gender);

        mCameraManager = CameraManager.getInstance(getActivity());
        mCameraManager.bindOptionMenuView(mFlashLight,null);
    }

    @OnClick({R.id.ib_close,R.id.ib_take_photo,R.id.tv_flashlight})
    @Override
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
        switch (v.getId()){
            case R.id.ib_close:
                if(getActivity() != null){
                    if(getActivity() instanceof FrontPicActivity){
                        ((FrontPicActivity)getActivity()).showCameraFragment(false);
                    }else if(getActivity() instanceof SidePicActivity){
                        ((SidePicActivity)getActivity()).showCameraFragment(false);
                    }
                }
                break;
            case R.id.ib_take_photo:
                mCameraContainer.takePicture();
                break;
            case R.id.tv_flashlight:
                mCameraContainer.switchFlashMode();
                break;
        }
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
