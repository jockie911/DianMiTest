package com.example.objLoader.module.personInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.ChangePwdActivity;
import com.example.objLoader.module.ChangeUsernameActivity;
import com.example.objLoader.module.MeasureRecordActivity;
import com.example.objLoader.module.setting.SettingActivity;
import com.example.objLoader.module.personInfo.presenter.InfoPresent;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.wedgit.ActionSheetDialog;
import com.example.objLoader.utils.Utils;
import com.example.objLoader.wedgit.CircleImageView;

import java.io.File;

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
    CircleImageView ivPic;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;

	private String username,mobile;
	private Uri imageUriFromCamera;
	private static final int CAMERA_REQUEST_CODE = 1,PHOTO_REQUEST_CODE = 2;
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

		String headPicPath = SPUtils.getInstance(mContext).getString("head_pic_path");
		if(headPicPath.equals("") || headPicPath.length() <= 0 ){
			ivPic.setImageResource(R.drawable.login_default);
		}else{
			File file = new File(headPicPath);
			if (file.isFile() && file.length() > 0){
				Glide.with(this).load(file).into(ivPic);
			}

			Glide.with(this).load(new File(headPicPath)).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivPic) {
				@Override
				protected void setResource(Bitmap resource) {
					RoundedBitmapDrawable circularBitmapDrawable =
							RoundedBitmapDrawableFactory.create(getResources(), resource);
					circularBitmapDrawable.setCircular(true);
					ivPic.setImageDrawable(circularBitmapDrawable);
				}
			});
		}

		mobile = SPUtils.getInstance(mContext).getString(IConstant.MOBILE);
		username = SPUtils.getInstance(mContext).getString("username");
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
				imageUriFromCamera = Utils.createImagePathUri(mContext);
				Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
				startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
			}
		}).addSheetItem(getResources().getString(R.string.album), ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
			@Override
			public void onClick(int which) {
				Intent photoIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
				startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
			}
		}).show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		username = SPUtils.getInstance(mContext).getString(IConstant.USERNAME);
		tv_username.setText(username);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String head_pic_path ;
		if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
			head_pic_path = Utils.setImg(getRealPathFromURI(imageUriFromCamera),ivPic);
			SPUtils.getInstance(mContext).putString("head_pic_path", head_pic_path);
		}
		if (requestCode == PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
			head_pic_path = Utils.setImg(getRealPathFromURI(data.getData()),ivPic);
			SPUtils.getInstance(mContext).putString("head_pic_path", head_pic_path);
		}
	}
}
