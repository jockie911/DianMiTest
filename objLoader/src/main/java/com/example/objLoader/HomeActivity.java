package com.example.objLoader;

import java.util.ArrayList;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.example.objLoader.activity.MeasureWeightAndHeightActivity;
import com.example.objLoader.activity.SizeActivity;
import com.example.objLoader.fragment.HomeFragment;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Toast;

import butterknife.Bind;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity  {

	@Bind(R.id.rb_celiang)
	RadioButton rb_celiang;
	@Bind(R.id.rb_chicun)
	RadioButton rb_chicun;
	@Bind(R.id.rb_youxi)
	RadioButton rb_youxi;
	@Bind(R.id.rb_dingzhi)
	RadioButton rb_dingzhi;
	@Bind(R.id.rb_shop)
	RadioButton rb_shop;

	public static HomeActivity homeActivity;
	private FragmentManager fragmentManager;
	private FragmentTransaction beTransaction;
	private HomeFragment homeFragment;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_home;
	}


	@Override
	protected void initData() {
		fragmentManager = getSupportFragmentManager();
		homeActivity = this;
		requestPermissions();
		switchFragment(0);
	}

	private void switchFragment(int index) {
		beTransaction = fragmentManager.beginTransaction();
		hideFragment(beTransaction);
		switch (index) {
		case 0:
			if (homeFragment == null) {
				homeFragment = new HomeFragment();
				beTransaction.add(R.id.fl_fragment, homeFragment);
			} else {
				beTransaction.show(homeFragment);
			}
			break;
			
		}
		beTransaction.commitAllowingStateLoss();
	}

	private void hideFragment(FragmentTransaction beTransaction2) {
		if (homeFragment != null) {
			beTransaction2.hide(homeFragment);
		}

	}

	@Override
	@OnClick({R.id.rb_celiang,R.id.rb_chicun,R.id.rb_youxi,R.id.rb_dingzhi,R.id.rb_shop,})
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rb_celiang:
			
			switchFragment(0);
			startActivity(new Intent(mContext, MeasureWeightAndHeightActivity.class));
			break;
		case R.id.rb_chicun:
			String str = SharedPreferencesDAO.getInstance(mContext).getString("json");
			if(str.length() <= 0 || str.equals("")){
				
				Toast.show(R.string.first_measure);
				
			}else{
				Intent intent = new Intent(mContext, SizeActivity.class);
				intent.putExtra("isData", false);
				startActivity(intent);
			}
			

			break;
		case R.id.rb_youxi:
			Toast.show(R.string.inaccessible);
//			rb_youxi.setChecked(false);
			break;
		case R.id.rb_dingzhi:
			Toast.show(R.string.inaccessible);
//			rb_youxi.setChecked(false);
			break;
		case R.id.rb_shop:
			Toast.show(R.string.inaccessible);
//			rb_youxi.setChecked(false);
			break;
		}

	}
	
	
	
	
	@Override
	protected void onResume() {
		super.onResume();

		if(SharedPreferencesDAO.getInstance(mContext).getString("phone_number").length() > 0){
			
		}else{
			SharedPreferencesDAO.getInstance(mContext).putBoolean("isLogin", false);
		}
		
	}
	
	@SuppressLint("Override") @Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		  //定位到这个权限
        if (requestCode==1){
            
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                Toast.show("权限申请成功");
                
            }else{
//               Toast.show("权限申请失败");
            }
        }

	}
}
