package com.example.objLoader.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.example.objLoader.R;
import com.example.objLoader.activity.MyLogDetailsActivity;
import com.example.objLoader.adapter.CommonAdapter;
import com.example.objLoader.adapter.ViewHolder;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.bean.MyLog;
import com.example.objLoader.bean.MyLog.Data;
import com.example.objLoader.global.BaseApp;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.Constants;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.Utils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

public class MyLogFragment extends BaseFragment{

	private View view;
	private Request<String> getAllLogRequest,deleteMyLogRequest;
	private String mobile;
	private ListView lv_my_log;
	private MyLog myLog;
	private CheckBox cb_delete_my_log;
	// 存放选中待删除记录的recId
	private List<String> recIdList = new ArrayList<String>();
	private List<Data> lists = new ArrayList<Data>();
//	private String objPath,objUrl;
	private String name,objUrl;
	private int mPosition;
	private TextView tv_my_log_name,tv_my_log_delete;
	private PopupWindow pop_delete_my_log;
	private Button btn_delete_my_log_confirm,btn_delete_my_log_cancel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_my_log, null);
		
		initView();
		initData();
		setEvent();
		
		return view;
	}
	
	private void searchAllLog() {
		
		mobile = SharedPreferencesDAO.getInstance(getActivity()).getString("phone_number");
		
		getAllLogRequest = NoHttp.createStringRequest(Constants.GET_ALL_MEASURE_INFO,RequestMethod.POST);
		
		getAllLogRequest.add("mobile", mobile);
		getAllLogRequest.add("string", Utils.MD5(mobile + Constants.MD5_KEY + mobile));
		
		CallServer.getInstance().add(getActivity(), getAllLogRequest, callBack, Constants.GET_ALL_MEASURE_INFO_WHAT, true, false,MyLog.class);
		
	}

	private HttpCallBack<String> callBack = new HttpCallBack<String>(){
		
		public void onSucceed(int what, BaseRequestBean bean) {
			
			if (what == Constants.GET_ALL_MEASURE_INFO_WHAT){

				myLog = (MyLog) bean;
				Data data;
				
				lists.clear();
				
				for (int i = 0; i < myLog.getData().size(); i++) {
					data = myLog.getData().get(i);
					lists.add(data);
				}
				
				
				
				lv_my_log.setAdapter(new CommonAdapter<Data>(getActivity(), lists, R.layout.item_my_log) {

					@Override
					public void convert(final int position, ViewHolder holder, Data t) {
						
						(tv_my_log_name = holder.getView(R.id.tv_my_log_name)).setText(t.getName());
						cb_delete_my_log = holder.getView(R.id.cb_delete_my_log);
						
						
						cb_delete_my_log.setOnCheckedChangeListener(new OnCheckedChangeListener() {
							
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								recIdList.clear();
								if(isChecked){
									lists.get(position).setIsSelected(isChecked);
								}else{
									lists.get(position).setIsSelected(isChecked);
								}
								
								for(int i = 0;i < lists.size();i++){
									if(lists.get(i).isSelected()){
											recIdList.add(lists.get(i).getId());
									}
								}
								
							}

						});
						
						tv_my_log_name.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								mPosition = position;
								name = myLog.getData().get(position).getObjname();
								objUrl = myLog.getData().get(position).getThreeshowurl();								
								android.widget.Toast toast;
								toast = android.widget.Toast.makeText(BaseApp.getInstance(), R.string.open_model, android.widget.Toast.LENGTH_LONG);
								toast.setGravity(Gravity.CENTER, 0, 0);
								toast.show();
								
								Intent intent = new Intent(getActivity(),MyLogDetailsActivity.class);
								intent.putExtra("myLog", myLog);
								intent.putExtra("position", mPosition);
								intent.putExtra("name", name);
								intent.putExtra("objUrl", objUrl);
								startActivity(intent);
								
							}
						});
						
					}
					
				});
			}else if(what == Constants.DELETE_MY_LOG_WHAT){
				recIdList.clear();
				Toast.show(bean.info);
				searchAllLog();
			}
			
			
			
		};
		
		
		public void onFailed(int what, String errorInfo) {
			
			Toast.show(errorInfo);
		};
		
	};



	@Override
	public void onClick(View v) {
		
		
		
		switch (v.getId()) {
		case R.id.btn_delete_my_log_confirm:
			
			pop_delete_my_log.dismiss();
			

			break;
			
		case R.id.btn_delete_my_log_cancel:
			
			pop_delete_my_log.dismiss();
			
			break;
			
		// 删除我的记录
		case R.id.tv_my_log_delete:
			
			if(recIdList.size() <= 0){
				
				Toast.show(R.string.delete_my_log);
			
			}else{
				
				AlertDialog.Builder builder = new Builder(getActivity(),R.style.AlertDialog);
				builder.setTitle(R.string.delete);
				builder.setMessage(R.string.delete_log);
				builder.setInverseBackgroundForced(true);						
				
				builder.setPositiveButton(R.string.confirm,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								deleteMyLog();
							}
						});
				builder.setNegativeButton(R.string.cancel, null).show();
				
				
				
				
			}
			
			break;

		}

	}
	
	/**
	 * 删除我的记录
	 */
	private void deleteMyLog() {

		deleteMyLogRequest = NoHttp.createStringRequest(Constants.DELETE_MY_LOG,RequestMethod.POST);
		
		StringBuffer recId = new StringBuffer();
		
		for(String s:recIdList){
			
			recId.append(s + ",");
			
		}
		
		String str = recId.substring(0, recId.length()-1);
		
		
		deleteMyLogRequest.add("recid", str);
		deleteMyLogRequest.add("mobile",mobile);
		deleteMyLogRequest.add("string",Utils.MD5(mobile + Constants.MD5_KEY + str));
		
		CallServer.getInstance().add(getActivity(), deleteMyLogRequest, callBack, Constants.DELETE_MY_LOG_WHAT, false, false,BaseRequestBean.class);
		
	}

	/**
	 * 弹出删除记录对话框
	 */
	private void showPop() {
		
		
		View delete_my_log = LayoutInflater.from(getActivity()).inflate(R.layout.pop_delete_my_log, null);
		
		btn_delete_my_log_confirm = (Button) delete_my_log.findViewById(R.id.btn_delete_my_log_confirm);
		btn_delete_my_log_cancel = (Button) delete_my_log.findViewById(R.id.btn_delete_my_log_cancel);
		
		btn_delete_my_log_confirm.setOnClickListener(this);
		btn_delete_my_log_cancel.setOnClickListener(this);
		
		pop_delete_my_log = new PopupWindow(delete_my_log,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,true);
		pop_delete_my_log.setTouchable(true);
		pop_delete_my_log.setBackgroundDrawable(new ColorDrawable(0));
		pop_delete_my_log.showAtLocation(view.findViewById(R.id.ll_my_log),Gravity.CENTER, 0, 0);
		
	}

	@Override
	protected void initView() {
		
		lv_my_log = (ListView) view.findViewById(R.id.lv_my_log);
		tv_my_log_delete = (TextView) view.findViewById(R.id.tv_my_log_delete);
		
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void setEvent() {
		tv_my_log_delete.setOnClickListener(this);

	}

	
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);

		if(!hidden){
			if(SharedPreferencesDAO.getInstance(getActivity()).getBoolean("isLogin")){
				searchAllLog();

			}
		}
		
	}
	
	
	
	@Override
	public void onResume() {
		super.onResume();
		
		if(SharedPreferencesDAO.getInstance(getActivity()).getBoolean("isLogin")){
			
			searchAllLog();
			
		}		
		
	}

}
