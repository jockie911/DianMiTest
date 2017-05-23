package com.example.objLoader.module.measure.present;

/**
 * Created by yc on 2017/5/23.
 */

public class MeasurePresent {

    public MeasurePresent(){

    }

    public void measure(){

    }
}


/**
 * 上传图片从服务器获取测量信息
 */
	/*private void measure() {
		picPath = SPUtils. getInstance(mContext).getString(
				"sidePath");
		if (picPath.equals("") || picPath.length() <= 0) {
			ToastUtils.show(R.string.selector_side_pic);
			return;
		} else {
			side_pic_path = picPath;
		}
		tv_start_measure.setText(R.string.measuring);

		time = Utils.getTime();
		SPUtils.getInstance(mContext).putString("time", time);
		mobile = SPUtils.getInstance(mContext).getString(
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

	}*/

/*
	private HttpCallBack<String> callBack = new HttpCallBack<String>() {

		public void onSucceed(int what, BaseRequestBean bean) {

			ToastUtils.show(R.string.measureing);

//			measureInfo = (MeasureInfo) bean;

//			objPath = AppConfig.getInstance().APP_PATH_ROOT + File.separator + measureInfo.getData().getObjname();
////			objUrl = measureInfo.getData().getThreeshowurl();
//			objUrl = measureInfo.getData().getObjurl();
//				ToastUtils.show(R.string.measureing);
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
//				SPUtils.getInstance(mContext).putString("sidePath", "");
//				SPUtils.getInstance(mContext).putString("frontPicPath", "");
//				FrontPicActivity.activity.finish();
//				finish();
		};

		public void onSucceed(int what, Response<String> response) {
			String string = response.get().toString();

			SPUtils.getInstance(mContext).putString("json", string);
			SPUtils.getInstance(mContext).putBoolean("isSave", false);

		};

		public void onFailed(int what, String errorInfo) {
			ToastUtils.show(errorInfo);
			sideWindow.dismiss();

		};
	};

	private OnUploadListener mOnUploadListener = new OnUploadListener() {

		*/
/**
 * 文件的上传状态记录.
 *//*

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
					ToastUtils.show(R.string.front_pic_not_found);
				} else {
					ToastUtils.show(R.string.side_pic_not_found);
				}

			}
		}
	};
*/

