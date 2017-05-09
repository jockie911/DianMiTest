package com.example.objLoader.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.bean.MeasureInfo;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.AppConfig;
import com.example.objLoader.utils.Constants;
import com.example.objLoader.utils.JLog;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.Utils;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnUploadListener;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.io.File;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.OnClick;

public class SidePicActivity extends BaseActivity {

	@Bind(R.id.iv_side)
	ImageView iv_side;
	@Bind(R.id.btn_side_camera)
	Button btn_side_camera;
	@Bind(R.id.btn_side_album)
	Button btn_side_album;
	@Bind(R.id.tv_start_measure)
	TextView tv_start_measure;

	private String side_pic_path = "", mobile;
	private TextView tv_upload_pic_text;
	private Request<String> uploadPicRequest;

	private Uri imageUriFromCamera;
	private static final int CAMERA_REQUEST_CODE = 1, PHOTO_REQUEST_CODE = 2;
	private PopupWindow sideWindow;
	private String time;
	private String picPath;
	/** 拍摄照片地址 */
	private String photoPath;
	/** 相册照片地址 */
	private String albumPath;


	@Override
	protected int getLayoutRes() {
		return R.layout.activity_side_pic;
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected void initData() {

		tvTitle.setText(R.string.side_pic);

		picPath = SharedPreferencesDAO.getInstance(mContext).getString(IConstant.SIDE_PIC_PATH);

		JLog.d("initData()" + side_pic_path);

		Glide.with(this).load(picPath).into(iv_side);

		View sideView = LayoutInflater.from(mContext).inflate(R.layout.pop_front, null);
		tv_upload_pic_text = (TextView) sideView.findViewById(R.id.tv_upload_pic_text);
		sideWindow = new PopupWindow(sideView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
	}

	@Override
	@OnClick({R.id.btn_side_camera,R.id.btn_side_album,R.id.tv_start_measure})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
			/** 打开相机 */
			case R.id.btn_side_camera:
				imageUriFromCamera = Utils.createImagePathUri(mContext);
				Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
				startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);

				break;
			/** 打开相册 */
			case R.id.btn_side_album:
				Intent photoIntent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
				startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
				break;
			case R.id.tv_start_measure:
				startActivity(new Intent(SidePicActivity.this,MeasureWeightAndHeightActivity.class));
				//			measure();
				break;
		}
	}

	/**
	 * 上传图片从服务器获取测量信息
	 */
	private void measure() {
		picPath = SharedPreferencesDAO. getInstance(mContext).getString(
				"sidePath");
		if (picPath.equals("") || picPath.length() <= 0) {
			Toast.show(R.string.selector_side_pic);
			return;
		} else {
			side_pic_path = picPath;
		}
		
		tv_start_measure.setText(R.string.measuring);
		
		time = Utils.getTime();
		SharedPreferencesDAO.getInstance(mContext).putString("time", time);
		mobile = SharedPreferencesDAO.getInstance(mContext).getString(
				"phone_number");
		uploadPicRequest = NoHttp.createStringRequest(
				Constants.GET_MEASURE_INFO, RequestMethod.POST);
		// 上传文件需要实现NoHttp的Binary接口，NoHttp默认实现了一个FileBinary

		String filePath = AppConfig.getInstance().APP_PATH_ROOT
				+ File.separator;
		// 正面照路径 front_pic_path
//		FileBinary frontPic = new FileBinary(new File(front_pic_path));
		// FileBinary frontPic = new FileBinary(new File(filePath +
		// "front.jpg"));
		// 侧面照路径 side_pic_path
		FileBinary sidePic = new FileBinary(new File(side_pic_path));
		// FileBinary sidePic = new FileBinary(new File(filePath + "side.jpg"));
		// 文件上传进度
//		frontPic.setUploadListener(0, mOnUploadListener);
		sidePic.setUploadListener(1, mOnUploadListener);
		uploadPicRequest.add("mobile", mobile);// 手机号
//		uploadPicRequest.add("height", height);// height 身高
//		uploadPicRequest.add("weight", weight);// weight 体重
		uploadPicRequest.add("timeStamp", time);
//		uploadPicRequest.add("front", frontPic);// 正面照
		uploadPicRequest.add("side", sidePic);// 侧面照
		uploadPicRequest.add("r1x", "804");//
		uploadPicRequest.add("r1y", "1422");//
		uploadPicRequest.add("r1w", "1500");//
		uploadPicRequest.add("r1h", "2300");//
		uploadPicRequest.add("r2x", "1340");//
		uploadPicRequest.add("r2y", "1440");//
		uploadPicRequest.add("r2w", "882");//
		uploadPicRequest.add("r2h", "2340");//

		uploadPicRequest.add("deviceid", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));// 设备号
//		uploadPicRequest.add(
//				"string",
//				Utils.MD5(time
//						+ Utils.MD5(mobile + Constants.MD5_KEY + height
//								+ weight)));

		CallServer.getInstance().add(this, uploadPicRequest, callBack,
				Constants.GET_MEASURE_INFO_WHAT, false, false,
				MeasureInfo.class);

	}

	private HttpCallBack<String> callBack = new HttpCallBack<String>() {

		public void onSucceed(int what, BaseRequestBean bean) {

			Toast.show(R.string.measureing);

//			measureInfo = (MeasureInfo) bean;
			
//			objPath = AppConfig.getInstance().APP_PATH_ROOT + File.separator + measureInfo.getData().getObjname();
////			objUrl = measureInfo.getData().getThreeshowurl();
//			objUrl = measureInfo.getData().getObjurl();
//				Toast.show(R.string.measureing);
//				Log.e("objUrl", objUrl);
//				Log.e("objname", measureInfo.getData().getObjname());
//				Log.e("threeshowurl", measureInfo.getData().getThreeshowurl());
//				Intent intent = new Intent(mContext, SizeActivity.class);
//				intent.putExtra("isData", true);
//				intent.putExtra("measureInfo", measureInfo);
//				intent.putExtra("time", time);
////				intent.putExtra("objPath", objPath);
//				intent.putExtra("objUrl", objUrl);
//				startActivity(intent);
//
//				SharedPreferencesDAO.getInstance(mContext).putString("sidePath", "");
//				SharedPreferencesDAO.getInstance(mContext).putString("frontPicPath", "");
//				FrontPicActivity.activity.finish();
//				finish();
		};

		public void onSucceed(int what, Response<String> response) {
			String string = response.get().toString();
			
			SharedPreferencesDAO.getInstance(mContext).putString("json", string);
			SharedPreferencesDAO.getInstance(mContext).putBoolean("isSave", false);
			
		};

		public void onFailed(int what, String errorInfo) {
			Toast.show(errorInfo);
			sideWindow.dismiss();

		};
	};

	private OnUploadListener mOnUploadListener = new OnUploadListener() {

		/**
		 * 文件的上传状态记录.
		 */
		private String[] uploadStatus = new String[2];

		@Override
		public void onProgress(int what, int progress) {
			String front_info = getString(R.string.side_upload_pic_front);
			String side_info = getString(R.string.side_upload_pic_side);
			if (what == 0) {
				tv_upload_pic_text.setText(front_info + "  " + progress + "%...");
			} else {
				tv_upload_pic_text.setText(side_info + "  " + progress + "%...");
			}

			sideWindow.showAtLocation(findViewById(R.id.ll_side),
					Gravity.CENTER, 0, 0);

		}

		@Override
		public void onStart(int what) {

		}

		@Override
		public void onCancel(int what) {
		}

		@Override
		public void onFinish(int what) {

			if (what == uploadStatus.length - 1) {

				tv_upload_pic_text.setText(R.string.side_puload_pic_success);
				sideWindow.dismiss();

			}

		}

		@Override
		public void onError(int what, Exception exception) {

			sideWindow.dismiss();

			AlertDialog.Builder builder = new Builder(mContext,R.style.AlertDialog);
			builder.setTitle(R.string.upload_pic_error);
			builder.setMessage(R.string.server_error);
														
			builder.setPositiveButton(R.string.confirm,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							measure();
						}
					});
			builder.setNegativeButton(R.string.cancel, null).show();

			if (exception instanceof NotFoundException) {

				if (what == 0) {
					Toast.show(R.string.front_pic_not_found);
				} else {
					Toast.show(R.string.side_pic_not_found);
				}

			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

			photoPath = getRealPathFromURI(imageUriFromCamera);
			side_pic_path = photoPath;
			Glide.with(this).load(photoPath).into(iv_side);

			SharedPreferencesDAO.getInstance(this).putString("sidePath",
					side_pic_path);

		}
		if (requestCode == PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {

			albumPath = getRealPathFromURI(data.getData()); // 图片文件路径
			side_pic_path = albumPath;
			Glide.with(this).load(albumPath).into(iv_side);

			SharedPreferencesDAO.getInstance(this).putString("sidePath",
					side_pic_path);
		}
	}
}
