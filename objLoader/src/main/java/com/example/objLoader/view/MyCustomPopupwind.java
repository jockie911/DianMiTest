package com.example.objLoader.view;




import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.PopupWindow;

public class MyCustomPopupwind extends PopupWindow{
	
	/**
	 * 
	 */
//	private ListView lv_home_city;
	public MyCustomPopupwind(Context context, View.OnClickListener onClick, int res) {
//		super(context, R.style.massage_dialog);
		 View contentView = LayoutInflater.from(context).inflate(res, null);
		 setContentView(contentView);
		 init();
		 // 未登录弹框
//		 if (res == R.layout.popupwind_not_login) {
//			 getContentView().findViewById(R.id.ll_pop_login).setOnClickListener(onClick);
//			 getContentView().findViewById(R.id.ll_pop_register).setOnClickListener(onClick);
//		 }
//		 // 已登录弹框
//		 if (res == R.layout.popupwind_in_login){
//			 getContentView().findViewById(R.id.ll_pop_change_pwd).setOnClickListener(onClick);
//			 getContentView().findViewById(R.id.ll_pop_exit).setOnClickListener(onClick);
//		 }
//		 if(res == R.layout.popupwind_search_item){
//			 getContentView().findViewById(R.id.ll_pop_shops).setOnClickListener(onClick);
//			 getContentView().findViewById(R.id.ll_pop_items).setOnClickListener(onClick);
//		 }
		 
	}
	
	public MyCustomPopupwind(Context context, AdapterView.OnItemClickListener onClick, int res) {
//		super(context, R.style.massage_dialog);
		 View contentView = LayoutInflater.from(context).inflate(res, null);
		 setContentView(contentView);
		 init();
		 
//		 if(res == R.layout.popupwind_city){
//			 lv_home_city = (ListView) getContentView().findViewById(R.id.lv_home_city);
//			 lv_home_city.setOnItemClickListener(onClick);
//			 
//		 }
		 
	}
	
	
	private void init() {
		// TODO Auto-generated method stub
		
        setWidth(AbsListView.LayoutParams.WRAP_CONTENT);
        setHeight(AbsListView.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        mpopup.setAnimationStyle(R.style.AnimBottom);	
        setBackgroundDrawable(new ColorDrawable(0));
	}

}
