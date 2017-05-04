package com.example.objLoader.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;

import com.example.objLoader.R;
import com.example.objLoader.fragment.DetailsSizeFragment;
import com.example.objLoader.fragment.ModelFragment;
import com.example.objLoader.fragment.MyLogFragment;
import com.example.objLoader.fragment.NullFragment;
import com.example.objLoader.global.AbActivityManager;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.global.MyAPP;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Toast;

import butterknife.Bind;
import butterknife.OnClick;

public class SizeActivity extends BaseActivity {

	@Bind(R.id.rb_mate)
	RadioButton rb_mate;
	@Bind(R.id.rb_my_log)
	RadioButton rb_my_log;
	@Bind(R.id.rb_model)
	RadioButton rb_model;
	@Bind(R.id.rb_details_size)
	RadioButton rb_details_size;

	private ViewPager viewpager;

	private FragmentManager fragmentManager;
	private FragmentTransaction beTransaction;

	private List<Fragment> list = new ArrayList<Fragment>();
	private DetailsSizeFragment detailsSizeFragment;
	private NullFragment nullFragment;
	private ModelFragment modelFragment;
	private MyLogFragment myLogFragment;
	private Boolean isData;
	// 当前选中的项
	private int currenttab;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_size;
	}

	@OnClick({R.id.rb_mate,R.id.rb_my_log,R.id.rb_model,R.id.rb_details_size})
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rb_details_size:

			
			 if(!isData){
//			 Toast.show(R.string.not_details_size);
			 }
			switchColor(0);
			switchFragment(0);
			
			break;

		case R.id.rb_mate:

			switchColor(1);
			switchFragment(1);
			
			Toast.show(R.string.inaccessible);
			break;
		case R.id.rb_model:

			 if(isData){
			
			 android.widget.Toast toast;
			 toast = android.widget.Toast.makeText(MyAPP.getInstance(),
			 R.string.open_model, android.widget.Toast.LENGTH_SHORT);
			 toast.setGravity(Gravity.CENTER, 0, 0);
			 toast.show();
			
			 }else{
				 
			 }
			switchColor(2);
			switchFragment(2);
			

			break;
		case R.id.rb_my_log:
			switchColor(3);
			
			switchFragment(3);
			if(!SharedPreferencesDAO.getInstance(mContext).getBoolean("isLogin")){
				startActivity(new Intent(mContext, LoginActivity.class));
			}
			break;
		}

	}

	@Override
	protected void initData() {
		isData = getIntent().getExtras().getBoolean("isData");
		fragmentManager = getSupportFragmentManager();

		switchFragment(0);
		switchColor(0);

	}

	private void switchColor(int index) {
		if (index == 0) {
			rb_details_size.setBackgroundColor(Color.parseColor("#0d548c"));
			rb_mate.setBackgroundColor(Color.WHITE);
			rb_model.setBackgroundColor(Color.WHITE);
			rb_my_log.setBackgroundColor(Color.WHITE);

		} else if (index == 1) {
			rb_mate.setBackgroundColor(Color.parseColor("#0d548c"));
			rb_details_size.setBackgroundColor(Color.WHITE);
			rb_model.setBackgroundColor(Color.WHITE);
			rb_my_log.setBackgroundColor(Color.WHITE);

		} else if (index == 2) {
			rb_model.setBackgroundColor(Color.parseColor("#0d548c"));
			rb_details_size.setBackgroundColor(Color.WHITE);
			rb_mate.setBackgroundColor(Color.WHITE);
			rb_my_log.setBackgroundColor(Color.WHITE);

		} else {
			rb_my_log.setBackgroundColor(Color.parseColor("#0d548c"));
			rb_details_size.setBackgroundColor(Color.WHITE);
			rb_mate.setBackgroundColor(Color.WHITE);
			rb_model.setBackgroundColor(Color.WHITE);

		}
		
	}
	
	private void switchFragment(int index) {
		beTransaction = fragmentManager.beginTransaction();
		hideFragment(beTransaction);
		switch (index) {
		case 0:
			if (detailsSizeFragment == null) {
				detailsSizeFragment = new DetailsSizeFragment();
				beTransaction.add(R.id.fl_fragment, detailsSizeFragment);
			} else {
				beTransaction.show(detailsSizeFragment);
			}
			break;
		case 1:
			if (nullFragment == null) {
				nullFragment = new NullFragment();
				beTransaction.add(R.id.fl_fragment, nullFragment);
			} else {
				beTransaction.show(nullFragment);
			}
			break;
		case 2:
			if (modelFragment == null) {
				modelFragment = new ModelFragment();
				beTransaction.add(R.id.fl_fragment, modelFragment);
			} else {
				beTransaction.show(modelFragment);
			}
			break;
		case 3:
			if (myLogFragment == null) {
				myLogFragment = new MyLogFragment();
				beTransaction.add(R.id.fl_fragment, myLogFragment);
			} else {
				beTransaction.show(myLogFragment);
			}
			break;
			
		}
		beTransaction.commitAllowingStateLoss();
	}

	private void hideFragment(FragmentTransaction beTransaction2) {
		if (detailsSizeFragment != null) {
			beTransaction2.hide(detailsSizeFragment);
		}
		if (nullFragment != null) {
			beTransaction2.hide(nullFragment);
		}
		if(modelFragment != null){
			beTransaction2.hide(modelFragment);
		}
		if(myLogFragment != null){
			beTransaction2.hide(myLogFragment);
		}

	}

}
