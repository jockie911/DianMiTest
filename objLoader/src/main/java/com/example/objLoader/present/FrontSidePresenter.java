package com.example.objLoader.present;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.fragment.PhotoCommandFragment;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.present.view.IFrontSideView;
import com.example.objLoader.module.measure.MeasureWeightAndHeightActivity;
import com.example.objLoader.module.measure.SidePicActivity;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.wedgit.AlertDialog;

import java.lang.ref.WeakReference;

/**
 * Created by yc on 2017/5/23.
 */

public class FrontSidePresenter<T extends BaseActivity> extends BasePresenter<IFrontSideView>{

    private PhotoCommandFragment photoCommandFragment;
    private final WeakReference<T> mActivity;

    public FrontSidePresenter(T activity){
        mActivity = new WeakReference<T>(activity);
    }

    /**
     * 是否是正面照片，需要考虑最近一次保存的性别,或者放在下一步进行判断
     * @param targetImageView
     */
    public void initBmpShow(ImageView targetImageView) {
        mBaseView.getGender();
        String picPath = SPUtils.getInstance().getString(mBaseView.isFrontPic() ? IConstant.FRONT_PIC_PATH : IConstant.SIDE_PIC_PATH);
        if(!TextUtils.isEmpty(picPath)){
            Glide.with(mActivity.get()).load(picPath).into(targetImageView);
        }else{
            showCameraFragment(true);
        }
    }

    public void showCameraFragment(boolean isShow) {
        if(mActivity.get() == null) return;
        if(photoCommandFragment == null)
            photoCommandFragment = new PhotoCommandFragment(mBaseView.isFrontPic(),mBaseView.getGender());
        FragmentTransaction fragmentTransaction = mActivity.get().getSupportFragmentManager().beginTransaction();
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
        boolean isFront = mBaseView.isFrontPic();
        String picPath = SPUtils.getInstance().getString(isFront ? IConstant.FRONT_PIC_PATH : IConstant.SIDE_PIC_PATH);
        if(TextUtils.isEmpty(picPath)){
            ToastUtils.show(isFront ? R.string.selector_front_pic : R.string.selector_side_pic);
            return;
        }
        Intent intent = new Intent(mActivity.get(),isFront ? SidePicActivity.class : MeasureWeightAndHeightActivity.class);
        intent.putExtra(IConstant.GENDER,mBaseView.getGender());
        if(!isFront) intent.putExtra(IConstant.GENDER,mBaseView.getGender());
        ((T)mActivity.get()).startActivity(intent);

    }

    /**
     * 从相册选取照片，ndk检查是否是正确格式的照片  （照片是否需要手动剪裁？？？）
     * @param picAbsPath
     */
    public void checkAlbumPicContainsData(String picAbsPath) {
        if(true){
            mBaseView.resultAlbumPicSuccess(picAbsPath);
        }else{
            mBaseView.resultAlbumPicError();
        }
    }

    /**
     * 照片不符合，让用户重新选择
     */
    public void showErrorDialog() {
        AlertDialog builder = new AlertDialog(mActivity.get()).builder();
        builder.setTitle(R.string.error_pic_model)
                .setMsg(mBaseView.isFrontPic()?R.string.choose_right_front_pic : R.string.choose_right_side_pic)
                .setNegativeButton(R.string.cancel,null)
                .setPositiveButton(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBaseView.openAlbumChoosePic();
                    }
                }).show();
    }
}
