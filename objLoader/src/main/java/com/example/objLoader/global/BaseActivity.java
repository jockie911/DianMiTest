package com.example.objLoader.global;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.utils.SystemBarTintManager;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.Calendar;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener{

	public static Context mContext;
	
	//手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
	protected float x1 = 0;
	protected float x2 = 0;
	protected float y1 = 0;
	protected float y2 = 0;
	protected View rootView;
	protected TextView tvRightTitle;
	protected TextView tvTitle;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEA);
		setStatusBar();
		setPhoneOrientation();
		rootView = LayoutInflater.from(this).inflate(getLayoutRes(), null);
		setContentView(rootView);
		initBaseTitleBar();
		ButterKnife.bind(this);
		initData();

		mContext = this;
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
		return true;
	}

	/**
	 * int resID
	 */
	protected abstract int getLayoutRes();

	protected abstract void initData();
//
//
//	protected abstract void initView();
//
//
//	protected abstract void setEvent();
	
	
	@SuppressWarnings("unchecked")
	public <T extends View> T findViewId(int resId){
		if(resId>0){
			return (T) findViewById(resId);
		}
		return null;
	}
	
	

	@SuppressLint("InlinedApi")
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
	
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
		super.onDestroy();
		// 退出APP时直接取消所有网络请求，这样会取消队列中所有的请求。
        CallServer.getInstance().cancelAll();

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
	protected void onResume() {
		super.onResume();
		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	@Override
	public void onClick(View v) {

	}

	protected boolean isDoubleClick(View v){
		Object tag = v.getTag(v.getId());
		long beforeTimemiles = tag != null ? (long) tag : 0;
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		v.setTag(v.getId(),timeInMillis);
	    return timeInMillis - beforeTimemiles < IConstant.NO_DOUBLE_CLICK_TIME;
	}
}
