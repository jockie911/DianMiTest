package com.example.objLoader.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.objLoader.R;
import com.example.objLoader.activity.DetailMeasureSizeActivity;
import com.example.objLoader.adapter.MeasureInfoAdapter;
import com.example.objLoader.bean.MeasureInfo;
import com.example.objLoader.bean.MeasureInfo.Info;
import com.example.objLoader.bean.MeasureRecordBean;
import com.yolanda.nohttp.rest.Request;

import java.util.List;

import butterknife.Bind;

@SuppressLint("ValidFragment")
public class DetailsSizeFragment extends com.example.objLoader.global.BaseFragment {

	private PopupWindow sizeNameWindow;
	private String sizeName;
	private EditText et_size_name;
	private Button btn_size_name_confirm;
	private Request<String> saveSizeNameRequest;
	private String mobile,time;
	private MeasureInfo measureInfo;
	private List<MeasureRecordBean.DataBean.InfoBean> info;
	private List<Info> lists;
	private MeasureInfoAdapter adapter;
	private Boolean isData;
	
	@Bind(R.id.lv_dtails_size)
	ListView mDetailDataLv;

	@Override
	protected View inflater(LayoutInflater inflater) {
		return inflater.inflate(R.layout.fragment_details_size, null);
	}

	@Override
	protected void initData() {
	/*	isData = getActivity().getIntent().getExtras().getBoolean("isData");
		String strJson = SPUtils.getInstance(getActivity()).getString("json");
		if(strJson.length() <= 0 || strJson.equals("")){
			startActivity(new Intent(getActivity(), MeasureWeightAndHeightActivity.class));
			getActivity().finish();
		}else if(isData){
			measureInfo = (MeasureInfo) getActivity().getIntent().getExtras().getSerializable("measureInfo");

			lists = new ArrayList<MeasureInfo.Info>();
			for(int i = 0;i < measureInfo.getData().getInfo().size();i++){

				info = measureInfo.getData().getInfo().get(i);
				lists.add(info);
			}
		}else{
			if(strJson.length() <= 0 || strJson.equals("")){
			}else{
				measureInfo = gson.fromJson(strJson, MeasureInfo.class);

				lists = new ArrayList<MeasureInfo.Info>();
				for(int i = 0;i < measureInfo.getData().getInfo().size();i++){

					info = measureInfo.getData().getInfo().get(i);
					lists.add(info);
				}
			}
		}*/


		adapter = new MeasureInfoAdapter(getActivity(),null, R.layout.item_measureinfo);
		mDetailDataLv.setAdapter(adapter);
		if(getActivity() != null){
			List<MeasureRecordBean.DataBean.InfoBean> detailInfo = ((DetailMeasureSizeActivity) getActivity()).getDetailInfo();
			adapter.addData(detailInfo);
		}
	}

	public void onClick(View v) {
		
		switch (v.getId()) {
//		case R.id.tv_save_details_size:
//
//
//			if(SPUtils.getInstance(getActivity()).getBoolean("isLogin")){
//
//				showPopup();
//
//			}else{
//				Toast.show(R.string.not_login);
//				startActivity(new Intent(getActivity(), LoginActivity.class));
//			}
//
//
//			break;

//		case R.id.btn_size_name_confirm:
//			sizeName = et_size_name.getText().toString().trim();
//			if(sizeName.length()<=0){
//				Toast.show(R.string.input_size_name);
//				return;
//			}else{
//				sizeNameWindow.dismiss();
//			}
//
//			saveSizeName();
//
//			break;
		}

	}

	/**
	 * 获取保存记录名称
	 */
//	private void saveSizeName() {
//		time = SPUtils.getInstance(getActivity()).getString("time");
//		mobile = SPUtils.getInstance(getActivity()).getString("phone_number");
//
//		saveSizeNameRequest = NoHttp.createStringRequest(Constants.SAVE_NAME, RequestMethod.POST);
//
//		saveSizeNameRequest.add("mobile", mobile);
//		saveSizeNameRequest.add("timeStamp", time);
//		saveSizeNameRequest.add("name",sizeName);
//		saveSizeNameRequest.add("string", Utils.MD5(time + Utils.MD5(mobile + Constants.MD5_KEY + sizeName)));
//
//		CallServer.getInstance().add(getActivity(), saveSizeNameRequest, callBack, Constants.SAVE_NAME_WHAT, true, false,BaseRequestBean.class);
//
//	}
//
//	private HttpCallBack<String> callBack = new HttpCallBack<String>(){
//
//		public void onSucceed(int what, BaseRequestBean bean) {
//
//			Toast.show(bean.info);
//			SPUtils.getInstance(getActivity()).putBoolean("isSave", true);
//
//		};
//
//
//		public void onFailed(int what, String errorInfo) {
//
//			Toast.show(errorInfo);
//
//		};
//
//	};
//
//
//	private void showPopup() {
//
//		View sizeNameView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_size_name, null);
//
//		btn_size_name_confirm = (Button) sizeNameView.findViewById(R.id.btn_size_name_confirm);
//
//		et_size_name = (EditText) sizeNameView.findViewById(R.id.et_size_name);
//
//		btn_size_name_confirm.setOnClickListener(this);
//
//		sizeNameWindow = new PopupWindow(sizeNameView,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,true);
//
//		sizeNameWindow.setTouchable(true);
//		sizeNameWindow.setBackgroundDrawable(new ColorDrawable(0));
//		sizeNameWindow.showAtLocation(rootView.findViewById(R.id.ll_details_size),Gravity.CENTER, 0, 0);
//
//	}


	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(isVisibleToUser){
			
		}
		
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
//
//		if(!SPUtils.getInstance(getActivity()).getBoolean("isSave")){
//			tv_save_details_size.setVisibility(View.VISIBLE);
//		}else{
//			tv_save_details_size.setVisibility(View.GONE);
//		}
		
		
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		
		if(!hidden){
//			if(!SPUtils.getInstance(getActivity()).getBoolean("isSave")){
//				tv_save_details_size.setVisibility(View.VISIBLE);
//			}else{
//				tv_save_details_size.setVisibility(View.GONE);
//			}
		}
		
	}
}
