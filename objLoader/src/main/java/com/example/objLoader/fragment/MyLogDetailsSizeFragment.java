package com.example.objLoader.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.objLoader.R;
import com.example.objLoader.adapter.MeasureInfoAdapter;
import com.example.objLoader.adapter.MyLogDetailsSizeAdapter;
import com.example.objLoader.bean.MyLog;
import com.example.objLoader.bean.MyLog.Info;

public class MyLogDetailsSizeFragment extends BaseFragment {
	
	
	private ListView lv_my_log_details_size;
	private View chatView;
	private MyLog myLog;
	private Info info;
	private List<Info> lists = new ArrayList<MyLog.Info>();
	private MyLogDetailsSizeAdapter adapter;
	private int position;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		chatView = inflater.inflate(R.layout.fragment_my_log_details_size, null);
		
		
		myLog = (MyLog) getActivity().getIntent().getExtras().get("myLog");
		position = getActivity().getIntent().getExtras().getInt("position");
		
		for(int i = 0;i<myLog.getData().get(position).getInfo().size();i++){
			info = myLog.getData().get(position).getInfo().get(i);
			lists.add(info);
		}
		
		initView();
		initData();
		setEvent();
		
		
		return chatView;
		
		
	}
	

	@Override
	public void onClick(View v) {
		
		
	}


	@Override
	protected void initView() {
		lv_my_log_details_size = (ListView) chatView.findViewById(R.id.lv_my_log_details_size);
		
	}


	@Override
	protected void initData() {
		
		adapter = new MyLogDetailsSizeAdapter(getActivity(),lists, R.layout.item_measureinfo);
		lv_my_log_details_size.setAdapter(adapter);
		
	}


	@Override
	protected void setEvent() {
		
	}
	public void finish(View v){
		getActivity().finish();
	}
}
