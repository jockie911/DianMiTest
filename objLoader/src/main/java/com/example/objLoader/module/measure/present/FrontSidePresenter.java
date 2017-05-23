package com.example.objLoader.module.measure.present;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.fragment.PhotoCommandFragment;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.global.BaseApp;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.measure.FrontSideView;
import com.example.objLoader.module.measure.MeasureWeightAndHeightActivity;
import com.example.objLoader.module.measure.SidePicActivity;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;

/**
 * Created by yc on 2017/5/23.
 */

public class FrontSidePresenter<T extends BaseActivity> {

    private T activity;
    private FrontSideView frontSideView;
    private PhotoCommandFragment photoCommandFragment;

    public FrontSidePresenter(T activity,FrontSideView frontSideView){
        this.activity = activity;
        this.frontSideView = frontSideView;
    }

    /**
     * 是否是正面照片，需要考虑最近一次保存的性别,或者放在下一步进行判断
     * @param targetImageView
     */
    public void initBmpShow(ImageView targetImageView) {
        frontSideView.getGender();
        String picPath = SPUtils.getInstance(BaseApp.getContext()).getString(frontSideView.isFrontPic() ? IConstant.FRONT_PIC_PATH : IConstant.SIDE_PIC_PATH);
        if(!TextUtils.isEmpty(picPath)){
            Glide.with(activity).load(picPath).into(targetImageView);
        }
    }

    public void showCameraFragment(boolean isShow) {
        if(photoCommandFragment == null)
            photoCommandFragment = new PhotoCommandFragment(frontSideView.isFrontPic(),frontSideView.getGender());
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if(isShow){
            if(!photoCommandFragment.isAdded()){
                fragmentTransaction.add(R.id.content_container,photoCommandFragment);
            }else{
                fragmentTransaction.show(photoCommandFragment);
            }
        }else{
            fragmentTransaction.hide(photoCommandFragment);
        }
        fragmentTransaction.commit();
    }

    /**
     * 下一步操作
     */
    public void nextStep() {
        boolean isFront = frontSideView.isFrontPic();
        String picPath = SPUtils.getInstance(BaseApp.getContext()).getString(isFront ? IConstant.FRONT_PIC_PATH : IConstant.SIDE_PIC_PATH);
        if(TextUtils.isEmpty(picPath)){
            ToastUtils.show(isFront ? R.string.selector_front_pic : R.string.selector_side_pic);
            return;
        }
        Intent intent = new Intent(activity,isFront ? SidePicActivity.class : MeasureWeightAndHeightActivity.class);
        intent.putExtra(IConstant.GENDER,frontSideView.getGender());
        if(!isFront) intent.putExtra(IConstant.GENDER,frontSideView.getGender());
        ((T)activity).startActivity(intent);

    }
}
