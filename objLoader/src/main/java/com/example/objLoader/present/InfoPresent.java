package com.example.objLoader.present;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.login.LoginActivity;
import com.example.objLoader.present.view.InfoView;
import com.example.objLoader.utils.FileUtil;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.wedgit.ActionSheetDialog;
import com.example.objLoader.wedgit.AlertDialog;
import com.example.objLoader.wedgit.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yc on 2017/5/22.
 */

public class InfoPresent<T extends BaseActivity> extends BasePresenter<InfoView>{

    private T mActivity;
    private File file2;
    private File file1;

    public InfoPresent(T mActivity){
        this.mActivity = mActivity;
    }

    public void logout(){
        final AlertDialog builder = new AlertDialog(mActivity).builder();
        builder.setTitle(R.string.logout);
        builder.setMsg(R.string.logout_msg);
        builder.setNegativeButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.getInstance().putString(IConstant.USERNAME, "");
                SPUtils.getInstance().putBoolean(IConstant.IS_LOGIN, false);
                Intent intent = new Intent(BaseApp.getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mActivity.startActivity(intent);
                mActivity.finish();
            }
        }).show();
    }

    public void clickAvatar() {
        new ActionSheetDialog(mActivity).builder().addSheetItem(mActivity.getResources().getString(R.string.camear), ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file1 = new File(FileUtil.getAppFoler());
                if(!file1.exists())
                    file1.mkdir();
                file2 = new File(file1,System.currentTimeMillis() + "temp.png");

                Uri uri = Uri.fromFile(file2);
                mBaseView.storeUri(uri);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file2));
                mActivity.startActivityForResult(cameraIntent, IConstant.CAMERA_REQUEST_CODE);
            }
        }).addSheetItem(mActivity.getResources().getString(R.string.album), ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
                mActivity.startActivityForResult(photoIntent, IConstant.ALBUM_REQUEST_CODE);
            }
        }).show();
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);
        intent.putExtra("return-data", true);
        mActivity.startActivityForResult(intent, IConstant.RESULT_CROP_CODE);
    }

    public void setImageToHeadView(Intent intent, CircleImageView ivAvatar) {
        if(intent == null) return;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");

            ivAvatar.setImageBitmap(photo);
            File nf = new File(FileUtil.getAppFoler());
            if(!nf.exists())
                nf.mkdir();
            File f = new File(nf.getPath(), "avatar.png");
            if(f.exists()){
                f.delete();
            }
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileOutputStream out = null;
            try {//打开输出流 将图片数据填入文件中
                out = new FileOutputStream(f);
                photo.compress(Bitmap.CompressFormat.PNG, 50, out);
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
