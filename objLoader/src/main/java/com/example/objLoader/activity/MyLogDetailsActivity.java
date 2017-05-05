package com.example.objLoader.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.objLoader.R;
import com.example.objLoader.adapter.FragmentAdapter;
import com.example.objLoader.bean.MyLog;
import com.example.objLoader.fragment.MyLogDetailsSizeFragment;
import com.example.objLoader.fragment.MyLogModelFragment;
import com.example.objLoader.global.AbActivityManager;
import com.example.objLoader.global.BaseActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.OnClick;

public class MyLogDetailsActivity extends BaseActivity {

	@Bind(R.id.ll_my_log_size)
	LinearLayout ll_my_log_size;
	@Bind(R.id.ll_my_log_model)
	LinearLayout ll_my_log_model;
	@Bind(R.id.tv_my_log_size)
	TextView tv_my_log_size;
	@Bind(R.id.tv_title_center)
	TextView tv_title_center;
	@Bind(R.id.tv_my_log_model)
	TextView tv_my_log_model;
	@Bind(R.id.iv_line)
	ImageView mTabLineIv;
	@Bind(R.id.id_page_vp)
	ViewPager mPageVp;

	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	private FragmentAdapter mFragmentAdapter;

	/**
	 * Fragment
	 */
	private MyLogDetailsSizeFragment myLogDetailsSizeFragment;
	private MyLogModelFragment myLogModelFragment;
	/**
	 * ViewPager的当前选中页
	 */
	private int currentIndex;
	/**
	 * 屏幕的宽度
	 */
	private int screenWidth;
	
	private MyLog myLog;
	private int position;
	

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_my_log_details;
	}


	@SuppressWarnings("deprecation")
	@Override
	protected void initData() {
		myLog = (MyLog)getIntent().getExtras().get("myLog");
		position = getIntent().getExtras().getInt("position");
		initTabLineWidth();

		String recored = getString(R.string.size);
		tv_title_center.setText(recored + " — " + myLog.getData().get(position).getName());
		
		myLogModelFragment = new MyLogModelFragment();
		myLogDetailsSizeFragment = new MyLogDetailsSizeFragment();
		mFragmentList.add(myLogDetailsSizeFragment);
		mFragmentList.add(myLogModelFragment);

		mFragmentAdapter = new FragmentAdapter(
				this.getSupportFragmentManager(), mFragmentList);
		mPageVp.setAdapter(mFragmentAdapter);
		mPageVp.setCurrentItem(0);

		mPageVp.setOnPageChangeListener(new OnPageChangeListener() {

			/**
			 * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
			 */
			@Override
			public void onPageScrollStateChanged(int state) {

			}

			/**
			 * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
			 * offsetPixels:当前页面偏移的像素位置
			 */
			@Override
			public void onPageScrolled(int position, float offset,
					int offsetPixels) {
				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
						.getLayoutParams();

				/**
				 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
				 * 设置mTabLineIv的左边距 滑动场景： 记3个页面, 从左到右分别为0,1,2 0->1; 1->2; 2->1;
				 * 1->0
				 */

				if (currentIndex == 0 && position == 0)// 0->1
				{
					lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
							* (screenWidth / 2));

				} else if (currentIndex == 1 && position == 0) // 1->0
				{
					lp.leftMargin = (int) (-(1 - offset)
							* (screenWidth * 1.0 / 2) + currentIndex
							* (screenWidth / 2));

				} else if (currentIndex == 1 && position == 1) // 1->2
				{
					lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
							* (screenWidth / 2));
				} else if (currentIndex == 2 && position == 1) // 2->1
				{
					lp.leftMargin = (int) (-(1 - offset)
							* (screenWidth * 1.0 / 2) + currentIndex
							* (screenWidth / 2));
				}
				mTabLineIv.setLayoutParams(lp);
			}

			@Override
			public void onPageSelected(int position) {
				resetTextView();
				switch (position) {
				case 0:
					tv_my_log_size.setTextColor(Color.WHITE);
					break;
				case 1:
					tv_my_log_model.setTextColor(Color.WHITE);
					break;
				}
				currentIndex = position;
			}
		});

	}

	/**
	 * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
	 */
	private void initTabLineWidth() {
		DisplayMetrics dpMetrics = new DisplayMetrics();
		getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(dpMetrics);
		screenWidth = dpMetrics.widthPixels;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
				.getLayoutParams();
		lp.width = screenWidth / 2;
		mTabLineIv.setLayoutParams(lp);
	}

	/**
	 * 重置颜色
	 */
	private void resetTextView() {
		tv_my_log_size.setTextColor(Color.BLACK);
		tv_my_log_model.setTextColor(Color.BLACK);
	}

	@Override
	@OnClick({R.id.ll_my_log_size,R.id.ll_my_log_model})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
		case R.id.ll_my_log_size:
			mPageVp.setCurrentItem(0);
			break;

		case R.id.ll_my_log_model:
			mPageVp.setCurrentItem(1);
			break;
		}

	}
	
	public void finish(View view){
		
		finish();
		
	}
}
