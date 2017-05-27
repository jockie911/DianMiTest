package com.example.objLoader.module.personInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.ChangeUsernameActivity;
import com.example.objLoader.module.MeasureRecordActivity;
import com.example.objLoader.module.setting.SettingActivity;
import com.example.objLoader.module.personInfo.presenter.InfoPresent;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.wedgit.ActionSheetDialog;
import com.example.objLoader.utils.Utils;
import com.example.objLoader.wedgit.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;

public class AccountInfoActivity extends BaseActivity {

    @Bind(R.id.rl_change_pwd)
    RelativeLayout rl_change_pwd;
    @Bind(R.id.rl_change_username)
    RelativeLayout rl_change_username;
    @Bind(R.id.tv_phone_number)
    TextView tv_phone_number;
    @Bind(R.id.tv_username)
    TextView tv_username;
    @Bind(R.id.ivPic)
    CircleImageView ivAvatar;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;

	private String username,mobile;
	private Uri imageUriFromCamera;
	private InfoPresent infoPresent;

	@Override
    protected int getLayoutRes() {
        return R.layout.activity_account_info;
    }

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected void initData() {
		ivRightTitleBar.setImageResource(R.drawable.exit);
		tvTitle.setText(R.string.account_info_title);

		String headPicPath = SPUtils.getInstance().getString("head_pic_path");
		if(headPicPath.equals("") || headPicPath.length() <= 0 ){
			ivAvatar.setImageResource(R.drawable.login_default);
		}else{
			File file = new File(headPicPath);
			if (file.isFile() && file.length() > 0){
				Glide.with(this).load(file).into(ivAvatar);
			}

			Glide.with(this).load(new File(headPicPath)).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivAvatar) {
				@Override
				protected void setResource(Bitmap resource) {
					RoundedBitmapDrawable circularBitmapDrawable =
							RoundedBitmapDrawableFactory.create(getResources(), resource);
					circularBitmapDrawable.setCircular(true);
					ivAvatar.setImageDrawable(circularBitmapDrawable);
				}
			});
		}

		mobile = SPUtils.getInstance().getString(IConstant.MOBILE);
		username = SPUtils.getInstance().getString("username");
		if(!TextUtils.isEmpty(mobile) && mobile.length() == 11){
		  	String maskNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
			tv_phone_number.setText(maskNumber);
		}
		tv_username.setText(username);

		infoPresent = new InfoPresent();
	}

	@Override
    @OnClick({R.id.rl_change_pwd,R.id.rl_change_username,R.id.iv_right_title_bar,R.id.ivPic,R.id.rl_measure_record,R.id.rel_setting})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
		case R.id.rl_change_pwd:
			startActivity(new Intent(this, ChangePwdActivity.class));
			break;
		case R.id.rl_change_username:
			startActivity(new Intent(this, ChangeUsernameActivity.class));
			break;
		case R.id.iv_right_title_bar:
			infoPresent.logout(this);
			break;
		case R.id.ivPic:
			showPopup();
			break;
		case R.id.rl_measure_record:
			startActivity(new Intent(this, MeasureRecordActivity.class));
			break;
		case R.id.rel_setting:
			startActivity(new Intent(this, SettingActivity.class));
			break;
		}
	}

	private void showPopup() {
		new ActionSheetDialog(this).builder().addSheetItem(getResources().getString(R.string.camear), ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
			@Override
			public void onClick(int which) {
				imageUriFromCamera = Utils.createImagePathUri(BaseApp.getContext());
				Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
				startActivityForResult(cameraIntent, IConstant.CAMERA_REQUEST_CODE);
			}
		}).addSheetItem(getResources().getString(R.string.album), ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
			@Override
			public void onClick(int which) {
				Intent photoIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
				startActivityForResult(photoIntent, IConstant.ALBUM_REQUEST_CODE);
			}
		}).show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		username = SPUtils.getInstance().getString(IConstant.USERNAME);
		tv_username.setText(username);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String head_pic_path ;
		if (requestCode == IConstant.CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//			head_pic_path = Utils.setImg(getRealPathFromURI(imageUriFromCamera),ivAvatar);
//			SPUtils.getInstance().putString("head_pic_path", head_pic_path);
			cropRawPhoto(data.getData(),1,1,1080,1080);
		}
		if (requestCode == IConstant.ALBUM_REQUEST_CODE && resultCode == RESULT_OK) {
//			head_pic_path = Utils.setImg(getRealPathFromURI(data.getData()),ivAvatar);
//			SPUtils.getInstance().putString("head_pic_path", head_pic_path);
			cropRawPhoto(data.getData(),1,1,1080,1080);
		}
		if(requestCode == IConstant.RESULT_CROP_CODE && requestCode == RESULT_OK){
			setImageToHeadView(data);
		}
	}

	/**
	 * 裁剪原始的图片
	 */
	public void cropRawPhoto(Uri uri,int aspectX,int aspectY,int bpWidth,int bpHeight) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");

		intent.putExtra("crop", "true");
		// aspectX , aspectY :宽高的比例
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		// outputX , outputY : 裁剪图片宽高
		intent.putExtra("outputX", bpWidth);
		intent.putExtra("outputY", bpHeight);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, IConstant.RESULT_CROP_CODE);
	}

	private void setImageToHeadView(Intent intent) {
		if(intent == null) return;
		Bundle extras = intent.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			ivAvatar.setImageBitmap(photo);
			Glide.with(this).load(photo).into(ivAvatar);

//新建文件夹 先选好路径 再调用mkdir函数 现在是根目录下面的Ask文件夹
			File nf = new File(Environment.getExternalStorageDirectory()+"/Ask");
			if(!nf.exists())
				nf.mkdir();
//在根目录下面的ASk文件夹下 创建okkk.jpg文件
			File f = new File(Environment.getExternalStorageDirectory()+"/Ask", System.currentTimeMillis() + "okkk.jpg");
			FileOutputStream out = null;
			try {//打开输出流 将图片数据填入文件中
				out = new FileOutputStream(f);
				photo.compress(Bitmap.CompressFormat.PNG, 90, out);
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
