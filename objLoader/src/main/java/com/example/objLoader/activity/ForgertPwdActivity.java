package com.example.objLoader.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.utils.Utils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

public class ForgertPwdActivity extends BaseActivity {

	@Bind(R.id.et_forget_pwd_mobile)
	EditText et_forget_pwd_mobile;
	@Bind(R.id.et_forget_pwd_auth_code)
	EditText et_forget_pwd_auth_code;
	@Bind(R.id.et_forget_pwd_password)
	EditText et_forget_pwd_password;
	@Bind(R.id.tv_forget_pwd_send_auth_code)
	TextView tv_forget_pwd_send_auth_code;

	private String mobile,auth_code,password,confirm_password;
	private Boolean isCodeRight = false;
	private Boolean isCommit = true;
	private Request<String> forgetPwdRequest;
	private Timer timer;


	EventHandler eh = new EventHandler() {

		@Override
		public void afterEvent(int event, int result, Object data) {
			Message msg = new Message();
			msg.arg1 = event;
			msg.arg2 = result;
			msg.obj = data;
			mHandler.sendMessage(msg);
		}
	};

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_forget_pwd;
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected void initData() {
		tvTitle.setText(R.string.forget_pwd_find_pwd);
		SMSSDK.registerEventHandler(eh);
	}

	@Override
	@OnClick({R.id.btn_forget_pwd_confirm,R.id.tv_forget_pwd_send_auth_code})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		mobile = et_forget_pwd_mobile.getText().toString().trim();
		auth_code = et_forget_pwd_auth_code.getText().toString().trim();
		password = et_forget_pwd_password.getText().toString().trim();
		switch (v.getId()) {
		case R.id.btn_forget_pwd_confirm:
			confirm();
			break;
		case R.id.tv_forget_pwd_send_auth_code:
			if (mobile.length() < 11) {
				ToastUtils.show(R.string.right_mobile);
				return;
			} else if (!Utils.isNetworkConnected(ForgertPwdActivity.this)) { // 检查网络连接
				ToastUtils.show(R.string.log_check_network);
				return;
			} else {
				SPUtils.getInstance(this).putString("phone_number", mobile);
			}
			// 发送验证码
			timerSmsCode();
			SMSSDK.getVerificationCode(IConstant.PHONE_AREA_CODE, mobile);
			break;
		}
	}

	private void confirm() {
		if (mobile.length() < 11) {
			ToastUtils.show(R.string.right_mobile);
			return;
		}
		if(auth_code.length() < 4){
			ToastUtils.show(R.string.auth_code_wrong);
			return;
		}
		if (isCommit) {
		// 提交验证码
		SMSSDK.submitVerificationCode(IConstant.PHONE_AREA_CODE, mobile, auth_code);
	}
		if(isCodeRight){
			
			forgetPwdRequest = NoHttp.createStringRequest(Constants.FORGET_PWD, RequestMethod.POST);
			
			forgetPwdRequest.add(IConstant.MOBILE, mobile);
			forgetPwdRequest.add(IConstant.PASSWORD, Utils.MD5(password));
			forgetPwdRequest.add(IConstant.STRING, Utils.MD5(mobile + Constants.MD5_KEY + Utils.MD5(password)));
			CallServer.getInstance().add(this, forgetPwdRequest, callBack, Constants.REGISTER_WHAT, true, false,BaseRequestBean.class);
		}
	}
	
	private HttpCallBack<BaseRequestBean> callBack = new HttpCallBack<BaseRequestBean>() {
		
		public void onSucceed(int what, BaseRequestBean bean) {
			ToastUtils.show(bean.info);
			finish();
		};
		
		public void onFailed(int what, String errorInfo) {
			ToastUtils.show(errorInfo);
		};
	};
	
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			// Log.e("event", "event=" + event);
			if (result == SMSSDK.RESULT_COMPLETE) {
				System.out.println("--------result" + event);
				// 短信注册成功后，返回MainActivity,然后提示新好友
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功

					ToastUtils.show(R.string.auth_code_right);
					isCodeRight = true;
					isCommit = false;

				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					// 已经验证
					ToastUtils.show(R.string.auth_code_already);
				}
			} else {
				isCodeRight = false;
				isCommit = true;
				// ((Throwable) data).printStackTrace();
				// ToastUtils.makeText(MainActivity.this, "验证码错误",
				// ToastUtils.LENGTH_SHORT).show();
				// ToastUtils.makeText(MainActivity.this, "123",
				// ToastUtils.LENGTH_SHORT).show();
				int status = 0;
				try {
					((Throwable) data).printStackTrace();
					Throwable throwable = (Throwable) data;

					JSONObject object = new JSONObject(throwable.getMessage());
					String des = object.optString("detail");
					status = object.optInt("status");
					if (!TextUtils.isEmpty(des)) {
						ToastUtils.show(des);
						return;
					}
				} catch (Exception e) {
					SMSLog.getInstance().w(e);
				}
			}

		};
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(forgetPwdRequest != null){
			forgetPwdRequest.cancel();
		}
	};

	/**
	 * 验证码提交 倒计时
	 */
	private void timerSmsCode(){
		tv_forget_pwd_send_auth_code.setClickable(false);
		timer = new Timer();
		timer.schedule(new MyTimerTask(System.currentTimeMillis()),0,1000);
	}

	private class MyTimerTask extends TimerTask {

		private long timell;

		public MyTimerTask(long l) {
			this.timell = l;
		}

		@Override
		public void run() {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					long l = System.currentTimeMillis();
					int t = (int) ((l - timell)/1000);
					if(t >= 60){
						tv_forget_pwd_send_auth_code.setText(R.string.input_send_auth_code);
						tv_forget_pwd_send_auth_code.setClickable(true);
						timer.cancel();
					}else{
						tv_forget_pwd_send_auth_code.setText(getResources().getString(R.string.remain_time)  + (60 - t)+ "");
					}
				}
			});
		}
	}
	
}
