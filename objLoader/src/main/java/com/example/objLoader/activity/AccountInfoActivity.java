package com.example.objLoader.activity;

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
import com.example.objLoader.activity.mywork.MeasureRecordActivity;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.utils.ActionSheetDialog;
import com.example.objLoader.utils.SharedPreferencesDAO;
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

		String headPicPath = SharedPreferencesDAO.getInstance(mContext).getString("head_pic_path");
		if(headPicPath.equals("") || headPicPath.length() <= 0 ){
			ivPic.setImageResource(R.drawable.login_default);
		}else{
//			// Get the dimensions of the View	视图的尺寸
//	        int targetW = ivPic.getWidth();
//	        int targetH = ivPic.getHeight();
//
//	        // Get the dimensions of the bitmap	位图的尺寸
//	        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//	        bmOptions.inJustDecodeBounds = true;
//	        BitmapFactory.decodeFile(headPicPath, bmOptions);
//	        int photoW = bmOptions.outWidth;
//	        int photoH = bmOptions.outHeight;
//
//	        bmOptions.inJustDecodeBounds = false;
//	        bmOptions.inPurgeable = true;
//
//	        Bitmap bitmap = BitmapFactory.decodeFile(headPicPath, bmOptions);
//	        ivPic.setImageBitmap(bitmap);
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

		mobile = SharedPreferencesDAO.getInstance(mContext).getString(IConstant.MOBILE);
		username = SharedPreferencesDAO.getInstance(mContext).getString("username");
		if(!TextUtils.isEmpty(mobile) && mobile.length() == 11){
		  	String maskNumber = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
			tv_phone_number.setText(maskNumber);
		}
		tv_username.setText(username);
	}

	@Override
    @OnClick({R.id.rl_change_pwd,R.id.rl_change_username,R.id.iv_right_title_bar,R.id.ivPic,R.id.rl_measure_record})
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
			showDialogLogout();
			break;
		case R.id.ivPic:
			showPopup();
			break;
		case R.id.rl_measure_record:
			startActivity(new Intent(this, MeasureRecordActivity.class));
			break;
		}
	}

	/**
	 * tell user weather choosed logout
	 */
	private void showDialogLogout() {
		final com.example.objLoader.utils.AlertDialog builder = new com.example.objLoader.utils.AlertDialog(this).builder();
		builder.setTitle(R.string.logout);
		builder.setMsg(R.string.logout_msg);
		builder.setNegativeButton(R.string.cancel, new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		}).setPositiveButton(R.string.ok, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesDAO.getInstance(mContext).putString(IConstant.USERNAME, "");
				SharedPreferencesDAO.getInstance(mContext).putBoolean(IConstant.IS_LOGIN, false);
				Intent intent = new Intent(mContext, LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
			}
		}).show();
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
		username = SharedPreferencesDAO.getInstance(mContext).getString(IConstant.USERNAME);
		tv_username.setText(username);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String head_pic_path ;
		if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
			head_pic_path = Utils.setImg(getRealPathFromURI(imageUriFromCamera),ivPic);
			SharedPreferencesDAO.getInstance(mContext).putString("head_pic_path", head_pic_path);
		}
		if (requestCode == PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
			head_pic_path = Utils.setImg(getRealPathFromURI(data.getData()),ivPic);
			SharedPreferencesDAO.getInstance(mContext).putString("head_pic_path", head_pic_path);
		}
	}
}
