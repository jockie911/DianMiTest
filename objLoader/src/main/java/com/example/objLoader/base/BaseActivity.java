package com.example.objLoader.base;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.utils.SystemBarTintManager;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener{

	protected View rootView;
	protected TextView tvRightTitle;
	protected TextView tvTitle;
	private BasePresenter presenter;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStatusBar();
		setPhoneOrientation();
		rootView = LayoutInflater.from(this).inflate(getLayoutRes(), null);
		setContentView(rootView);
		initBaseTitleBar();
		ButterKnife.bind(this);

		presenter = initPresenter();
		initData();

		AbActivityManager.getInstance().addActivity(this);
		initSwipeBack();
	}

	protected void initBaseTitleBar(){
		if(!isHavaBaseTitleBar()) return;
		rootView.findViewById(R.id.iv_left_title_bar).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		tvRightTitle = (TextView) rootView.findViewById(R.id.tv_right_title_bar);
	};

	/**
	 * init activity滑动退出
	 */
	protected void initSwipeBack() {
		if(isSwipeBack()){
			SwipeBackHelper.onCreate(this);
			SwipeBackHelper.getCurrentPage(this)
					.setSwipeBackEnable(true)
					.setSwipeSensitivity(0.5f)
					.setSwipeRelateEnable(true)
					.setSwipeEdgePercent(0.15f)
					.setSwipeRelateOffset(500)
					.setClosePercent(0.5f);
		}
	}

	protected boolean isHavaBaseTitleBar(){
		return false;
	}

	/**
	 * 控制activity是否可以滑动退出
	 * @return
	 */
	protected boolean isSwipeBack(){
		return false;
	}

	/**
	 * int resID
	 */
	protected abstract int getLayoutRes();

	protected abstract void initData();

	/**
	 * 描述：Activity结束.
	 * 
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		AbActivityManager.getInstance().removeActivity(this);

		super.finish();
	}
	
	/**
     * 根据图片地址URI获取图片本地路径
     * @param contentUri
     * @return
     */
    @SuppressWarnings("deprecation")
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
	
	@Override
	protected void onDestroy() {
		if(isSwipeBack())
			SwipeBackHelper.onDestroy(this);
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm != null)
			imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//		SoftInputUtils.closeSoftInput(this);
        CallServer.getInstance().cancelAll();
		if (presenter != null) {
			presenter.detachView();
		}
		super.onDestroy();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		if(isSwipeBack())
			SwipeBackHelper.onPostCreate(this);
	}
	
	public void finish(View v){
		finish();
	}


	/** 设置屏幕竖屏*/
	private void setPhoneOrientation() {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	protected void setStatusBar() {
//		getWindow().setFlags(
//				WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(Color.TRANSPARENT);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setNavigationBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.color_base_deep);// 通知栏所需颜色
		tintManager.setNavigationBarTintResource(R.color.color_base_deep);
	}

	@Override
	public void onClick(View v) {

	}

	/**
	 * 避免快速操作造成连续点击
	 * @param v
	 * @return
	 */
	protected boolean isDoubleClick(View v){
		Object tag = v.getTag(v.getId());
		long beforeTimemiles = tag != null ? (long) tag : 0;
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		v.setTag(v.getId(),timeInMillis);
	    return timeInMillis - beforeTimemiles < IConstant.NO_DOUBLE_CLICK_TIME;
	}


	@Override
	protected void onStart() {
		Glide.with(this).resumeRequestsRecursive();
		super.onStart();
	}

	@Override
	protected void onPause() {
		Glide.with(this).pauseRequestsRecursive();
		super.onPause();
	}

	protected BasePresenter initPresenter() {
		return null;
	}

	protected void requestPermissions(boolean ...onlyRequestCamera){

		PackageManager pkgManager = getPackageManager();
		// 写SD卡权限
		boolean sdCardWritePermission = pkgManager.checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, getPackageName()) == PackageManager.PERMISSION_GRANTED;
		// 读SD卡权限
		boolean sdCaredReadPermission =  pkgManager.checkPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE, getPackageName()) == PackageManager.PERMISSION_GRANTED;
		// read phone state用于获取 imei 设备信息
		boolean phoneSatePermission = pkgManager.checkPermission(Manifest.permission.READ_PHONE_STATE, getPackageName()) == PackageManager.PERMISSION_GRANTED;
		// 打开相机
		boolean openCameraPermission = pkgManager.checkPermission(Manifest.permission.CAMERA, getPackageName()) == PackageManager.PERMISSION_GRANTED;

		if (Build.VERSION.SDK_INT >= 23) {
			ArrayList<String> list=new ArrayList<>();

			if (!sdCardWritePermission ){
				list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
			}

			if (!sdCaredReadPermission ){
				list.add(Manifest.permission.READ_EXTERNAL_STORAGE);
			}

			if (!phoneSatePermission && onlyRequestCamera.length == 0){
				list.add(Manifest.permission.READ_PHONE_STATE);
			}

			if (!openCameraPermission){
				list.add(Manifest.permission.CAMERA);
			}
			if (list.size()>0){
				String[] permissions=new String[list.size()];
				permissions=list.toArray(permissions);
				ActivityCompat.requestPermissions(
						this, permissions, 0);
			}
		}
	}
}
