package com.example.objLoader.module.personInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;
import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.ChangeUsernameActivity;
import com.example.objLoader.module.MeasureRecordActivity;
import com.example.objLoader.module.setting.SettingActivity;
import com.example.objLoader.module.personInfo.presenter.InfoPresent;
import com.example.objLoader.utils.FileUtil;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.wedgit.ActionSheetDialog;
import com.example.objLoader.utils.Utils;
import com.example.objLoader.wedgit.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;

public class AccountInfoActivity extends BaseActivity implements InfoView{

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
	private InfoPresent infoPresent;
	private Uri storeUri;

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

		File file = new File(FileUtil.getAppFoler(), "avatar.png");
		if(file.exists() && file.length() > 0){
			Glide.with(this).load(file).signature(new StringSignature(System.currentTimeMillis() + "")).into(ivAvatar);
		}

		mobile = SPUtils.getInstance().getString(IConstant.MOBILE);
		username = SPUtils.getInstance().getString("username");
		if(!TextUtils.isEmpty(mobile) && mobile.length() == 11){
		  	String maskNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
			tv_phone_number.setText(maskNumber);
		}
		tv_username.setText(username);
	}

	@Override
	protected BasePresenter initPresenter() {
		infoPresent = new InfoPresent(this);
		infoPresent.attachView(this);
		return infoPresent;
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
			infoPresent.logout();
			break;
		case R.id.ivPic:
			infoPresent.clickAvatar();
			break;
		case R.id.rl_measure_record:
			startActivity(new Intent(this, MeasureRecordActivity.class));
			break;
		case R.id.rel_setting:
			startActivity(new Intent(this, SettingActivity.class));
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		username = SPUtils.getInstance().getString(IConstant.USERNAME);
		tv_username.setText(username);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode){
			case IConstant.CAMERA_REQUEST_CODE:
				if(resultCode == RESULT_OK){
					infoPresent.cropRawPhoto(storeUri);
				}
				break;
			case IConstant.ALBUM_REQUEST_CODE:
				if(data != null)
					infoPresent.cropRawPhoto(data.getData());
				break;
			case IConstant.RESULT_CROP_CODE:
				if(data != null)
					infoPresent.setImageToHeadView(data,ivAvatar);
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void storeUri(Uri uri) {
		storeUri = uri;
	}
}
