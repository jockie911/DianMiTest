package com.example.objLoader.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.activity.LoginActivity;
import com.example.objLoader.activity.MeasureActivity;
import com.example.objLoader.adapter.MeasureInfoAdapter;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.bean.MeasureInfo;
import com.example.objLoader.bean.MeasureInfo.Info;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.Constants;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.Utils;
import com.google.gson.Gson;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

public class DetailsSizeFragment extends BaseFragment {

	private TextView tv_save_details_size;
	private PopupWindow sizeNameWindow;
	private String sizeName;
	private EditText et_size_name;
	private Button btn_size_name_confirm;
	private Request<String> saveSizeNameRequest;
	private String mobile,time;
	private MeasureInfo measureInfo;
	private Info info;
	private List<Info> lists;
	private ListView lv_dtails_size;
	private MeasureInfoAdapter adapter;
	private Boolean isData;
	
	private Gson gson = new Gson();
	
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_details_size, null);
		
		isData = getActivity().getIntent().getExtras().getBoolean("isData");
		
		String strJson = SharedPreferencesDAO.getInstance(getActivity()).getString("json");
		
		
		
		if(strJson.length() <= 0 || strJson.equals("")){
			startActivity(new Intent(getActivity(), MeasureActivity.class));
			getActivity().finish();
		}else if(isData){
			measureInfo = (MeasureInfo) getActivity().getIntent().getExtras().getSerializable("measureInfo");

			lists = new ArrayList<MeasureInfo.Info>();
			for(int i = 0;i < measureInfo.getData().getInfo().size();i++){
				
				info = measureInfo.getData().getInfo().get(i);
				lists.add(info);
				
			}
				initView();
				initData();
				setEvent();
			return view;
			}else{
				if(strJson.length() <= 0 || strJson.equals("")){
					return view;
				}else{
					measureInfo = gson.fromJson(strJson, MeasureInfo.class);
					
					lists = new ArrayList<MeasureInfo.Info>();
					for(int i = 0;i < measureInfo.getData().getInfo().size();i++){
						
						info = measureInfo.getData().getInfo().get(i);
						lists.add(info);
					}
					
					initView();
					initData();
					setEvent();
					
					return view;
				}
				
			}
		
		
		return view;
	}
	
	
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.tv_save_details_size:
			
			
			if(SharedPreferencesDAO.getInstance(getActivity()).getBoolean("isLogin")){
				
				showPopup();
				
			}else{
				Toast.show(R.string.not_login);
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			
			
			break;

		case R.id.btn_size_name_confirm:
			sizeName = et_size_name.getText().toString().trim();
			if(sizeName.length()<=0){
				Toast.show(R.string.input_size_name);
				return; 
			}else{
				sizeNameWindow.dismiss();
			}
			
			saveSizeName();
			
			break;
		}

	}

	/**
	 * 获取保存记录名称
	 */
	private void saveSizeName() {
		time = SharedPreferencesDAO.getInstance(getActivity()).getString("time");
		mobile = SharedPreferencesDAO.getInstance(getActivity()).getString("phone_number");
		
		saveSizeNameRequest = NoHttp.createStringRequest(Constants.SAVE_NAME, RequestMethod.POST);
		
		saveSizeNameRequest.add("mobile", mobile);
		saveSizeNameRequest.add("timeStamp", time);
		saveSizeNameRequest.add("name",sizeName);
		saveSizeNameRequest.add("string", Utils.MD5(time + Utils.MD5(mobile + Constants.MD5_KEY + sizeName)));
		
		CallServer.getInstance().add(getActivity(), saveSizeNameRequest, callBack, Constants.SAVE_NAME_WHAT, true, false,BaseRequestBean.class);
		
	}

	private HttpCallBack<String> callBack = new HttpCallBack<String>(){
		
		public void onSucceed(int what, BaseRequestBean bean) {
			
			Toast.show(bean.info);
			SharedPreferencesDAO.getInstance(getActivity()).putBoolean("isSave", true);
			
		};
		
		
		public void onFailed(int what, String errorInfo) {
			
			Toast.show(errorInfo);
			
		};
		
	};


	private void showPopup() {
		
		View sizeNameView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_size_name, null);
		
		btn_size_name_confirm = (Button) sizeNameView.findViewById(R.id.btn_size_name_confirm);
		
		et_size_name = (EditText) sizeNameView.findViewById(R.id.et_size_name);
		
		btn_size_name_confirm.setOnClickListener(this);
		
		sizeNameWindow = new PopupWindow(sizeNameView,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,true);
		
		sizeNameWindow.setTouchable(true);
		sizeNameWindow.setBackgroundDrawable(new ColorDrawable(0));
		sizeNameWindow.showAtLocation(view.findViewById(R.id.ll_details_size),Gravity.CENTER, 0, 0);
		
	}

	@Override
	protected void initView() {
		tv_save_details_size = (TextView) view.findViewById(R.id.tv_save_details_size);
		lv_dtails_size = (ListView) view.findViewById(R.id.lv_dtails_size);
	}

	@Override
	protected void initData() {
		
		adapter = new MeasureInfoAdapter(getActivity(),lists, R.layout.item_measureinfo);
		lv_dtails_size.setAdapter(adapter);
	}

	@Override
	protected void setEvent() {
		tv_save_details_size.setOnClickListener(this);

	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(isVisibleToUser){
			
		}
		
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		if(!SharedPreferencesDAO.getInstance(getActivity()).getBoolean("isSave")){
			tv_save_details_size.setVisibility(View.VISIBLE);
		}else{
			tv_save_details_size.setVisibility(View.GONE);
		}
		
		
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		
		if(!hidden){
			if(!SharedPreferencesDAO.getInstance(getActivity()).getBoolean("isSave")){
				tv_save_details_size.setVisibility(View.VISIBLE);
			}else{
				tv_save_details_size.setVisibility(View.GONE);
			}
		}
		
	}
	

}
