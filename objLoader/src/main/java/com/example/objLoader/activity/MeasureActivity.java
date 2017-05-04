package com.example.objLoader.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.objLoader.R;
import com.example.objLoader.global.AbActivityManager;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.utils.OptionsPopupWindow;
import com.example.objLoader.utils.OptionsPopupWindow.OnOptionsSelectListener;
import com.example.objLoader.utils.Toast;

import butterknife.Bind;
import butterknife.OnClick;

public class MeasureActivity extends BaseActivity {

	@Bind(R.id.iv_measure_right)
	ImageView iv_measure_right;
	@Bind(R.id.et_height)
	EditText et_height;
	@Bind(R.id.et_weight)
	EditText et_weight;

	private String height = "",weight = "";
	private OptionsPopupWindow weightPopupWindow,heightPopupWindow;
	
	private ArrayList<String> heightList = new ArrayList<String>();
	private ArrayList<String> weightList = new ArrayList<String>();
	public static MeasureActivity activity;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_measure;
	}

	@Override
	protected void initData() {
		heightPopupWindow = new OptionsPopupWindow(mContext);
		weightPopupWindow = new OptionsPopupWindow(mContext);
		activity = this;
		getChoiceValue();

		for (int i = 45; i < 91; i++) {
			weightList.add(i + "");
		}
		for (int i = 140; i < 201; i++) {
			heightList.add(i + "");
		}
		
	}



	@Override
	@OnClick({R.id.iv_measure_right,R.id.et_height,R.id.et_weight})
	public void onClick(View v) {
		height = et_height.getText().toString().trim();
		weight = et_weight.getText().toString().trim();
		
		switch (v.getId()) {
		case R.id.iv_measure_right:
			
			if(height.equals("") || weight.equals("") || height.length() <= 0 || weight.length() <= 0){
				
				Toast.show(R.string.input_weight_height);
				return;
			}
			
			Intent intent = new Intent(mContext, FrontPicActivity.class);
			intent.putExtra("height", height);
			intent.putExtra("weight", weight);
			startActivity(intent);
			
			break;
			
		case R.id.et_weight:
			
			weightPopupWindow.setPicker(weightList);
//			weightPopupWindow.setTouchable(true);
//			weightPopupWindow.setOutsideTouchable(true);
//			weightPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
			weightPopupWindow.showAtLocation(findViewById(R.id.ll_measure), Gravity.BOTTOM, 0, 0);
			
			break;
			
			
		case R.id.et_height:
			
			heightPopupWindow.setPicker(heightList);
//			heightPopupWindow.setOutsideTouchable(true);
//			heightPopupWindow.setTouchable(true);
//			weightPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
			heightPopupWindow.showAtLocation(findViewById(R.id.ll_measure), Gravity.BOTTOM, 0, 0);
			
			break;

		}
	}
	
	
	
	
	
	private void getChoiceValue(){
		weightPopupWindow.setOnoptionsSelectListener(new OnOptionsSelectListener() {
			
			@Override
			public void onOptionsSelect(int options1, int option2, int options3) {
				
				et_weight.setText(weightList.get(options1));
				
			}
		});
		
		heightPopupWindow.setOnoptionsSelectListener(new OnOptionsSelectListener() {
			
			@Override
			public void onOptionsSelect(int options1, int option2, int options3) {
				
				et_height.setText(heightList.get(options1));
				
			}
		});
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		
		if (weightPopupWindow != null && weightPopupWindow.isShowing()) {

			weightPopupWindow.dismiss();

			weightPopupWindow = null;

			}
		
		if (heightPopupWindow != null && heightPopupWindow.isShowing()) {

			heightPopupWindow.dismiss();

			heightPopupWindow = null;

			}
		
		
		
		//继承了Activity的onTouchEvent方法，直接监听点击事件
		 if(event.getAction() == MotionEvent.ACTION_DOWN) {
		  //当手指按下的时候
		  x1 = event.getX();
		  y1 = event.getY();
		 }
		 if(event.getAction() == MotionEvent.ACTION_UP) {
		  //当手指离开的时候
		  x2 = event.getX();
		  y2 = event.getY();
		  if(y1 - y2 > 50) {
			  // 向上滑;
		  } else if(y2 - y1 > 50) {
			  // 向下滑
		  } else if(x1 - x2 > 50) {
			  // 向左滑
			  height = et_height.getText().toString().trim();
			  weight = et_weight.getText().toString().trim();
			  if(height.equals("") || weight.equals("") || height.length() <= 0 || weight.length() <= 0){
					
					Toast.show(R.string.input_weight_height);
					return super.onTouchEvent(event);
				}
				
				
				Intent intent = new Intent(mContext, FrontPicActivity.class);
				intent.putExtra("height", height);
				intent.putExtra("weight", weight);
				startActivity(intent);
			  
			  
		  } else if(x2 - x1 > 50) {
			  // 向右滑
			  		finish();
		  }
		 }
		
		
		
		return super.onTouchEvent(event);
	}
	
	public void finish(View view){
		finish();
	}

}
