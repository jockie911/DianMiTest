package com.example.objLoader.fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.objLoader.R;
import com.example.objLoader.activity.AccountInfoActivity;
import com.example.objLoader.activity.LoginActivity;
import com.example.objLoader.bean.ADInfo;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.view.imagecycle.ImageCycleView;
import com.example.objLoader.view.imagecycle.ImageCycleView.ImageCycleViewListener;
import com.example.objLoader.HomeActivity;

public class HomeFragment extends BaseFragment {
	
	private View homeView;
	private ImageView iv_home_user;
	private TextView tv_home_date;
	private ImageCycleView icy_home;
	private int images[] = {R.drawable.home_logo_1,R.drawable.home_logo_2,R.drawable.home_logo_3,R.drawable.home_logo_4};
	
	private ArrayList<ADInfo> infos;
	private HomeActivity activity;
	
	@SuppressLint("InflateParams") @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity = (HomeActivity) getActivity();
		homeView = inflater.inflate(R.layout.fragment_home, null);
		
		
		
		initView();
		initData();
		setEvent();
		
		
		return homeView;
		
	}
	
	@Override
	protected void setEvent() {
		
		iv_home_user.setOnClickListener(this);
		
	}


	@SuppressLint("SimpleDateFormat") 
	@Override
	protected void initData() {
		Date date = new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date); 
		tv_home_date.setText(time);
		
		infos = new ArrayList<ADInfo>();

		for (int i = 0; i < images.length; i++) {
			ADInfo info = new ADInfo();
			infos.add(info);
		}
		icy_home.setImageResources(infos, mAdCycleViewListener);
	}

	@Override
	protected void initView() {
		
		iv_home_user = (ImageView) homeView.findViewById(R.id.iv_home_user);
		tv_home_date = (TextView) homeView.findViewById(R.id.tv_home_date);
		icy_home = (ImageCycleView) homeView.findViewById(R.id.icy_home);
		
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_home_user:
			
			
			if(SharedPreferencesDAO.getInstance(getActivity()).getBoolean("isLogin")){
				
				
				startActivity(new Intent(activity, AccountInfoActivity.class));
				
			}else{
				startActivity(new Intent(activity, LoginActivity.class));
			}
			
			
			break;

		}

	}
	
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int posaition, View imageView) {
			
			
		}

		@Override
		public void displayImage(String imageURL, ImageView imageView,int position) {
			
			if(position == 0){
				imageView.setImageResource(R.drawable.home_logo_1);
			}
			if(position == 1){
				imageView.setImageResource(R.drawable.home_logo_2);
			}
			if(position == 2){
				imageView.setImageResource(R.drawable.home_logo_3);
			}
			if(position == 3){
				imageView.setImageResource(R.drawable.home_logo_4);
			}
			
		}
	};

}
