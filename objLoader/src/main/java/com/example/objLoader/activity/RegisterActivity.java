package com.example.objLoader.activity;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.Constants;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.Utils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity{

	@Bind(R.id.et_mobile)
	EditText et_mobile;
	@Bind(R.id.et_auth_code)
	EditText et_auth_code;
	@Bind(R.id.et_register_password)
	EditText et_register_password;
	@Bind(R.id.et_confirm_password)
	EditText et_confirm_password;
	@Bind(R.id.tv_send_auth_code)
	TextView tv_send_auth_code;

	private String mobile,auth_code,password,confirm_password;
	private Boolean isCodeRight = false;
	private Boolean isCommit = true;
	private Request<String> registerRuquest;
	
	
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
		return R.layout.activity_register;
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected void initData() {
		SMSSDK.registerEventHandler(eh);
		tvTitle.setText(R.string.register_title);
	}

	@Override
	@OnClick({R.id.btn_register,R.id.tv_send_auth_code})
	public void onClick(View v) {
        if(isDoubleClick(v)) return;
		mobile = et_mobile.getText().toString().trim();
		auth_code = et_auth_code.getText().toString().trim();
		password = et_register_password.getText().toString().trim();
		confirm_password = et_confirm_password.getText().toString().trim();
		
		switch (v.getId()) {
		case R.id.btn_register:
			register();
			break;
		case R.id.tv_send_auth_code:

//			if (mobile.length() < 11) {
//				Toast.show(R.string.right_mobile);
//				return;
//			} else
				if (!Utils.isNetworkConnected(RegisterActivity.this)) { // 检查网络连接
				Toast.show(R.string.log_check_network);
				return;
			} 
			// 发送验证码
			SMSSDK.getVerificationCode("86", mobile);
			break;

		}

	}

	private void register() {
		
//		if (mobile.length() < 11) {
//			Toast.show(R.string.right_mobile);
//			return;
//		} 
		
//		if (!password.equals(confirm_password)) {
//			Toast.show(R.string.pwd_not_right);
//			return;
//		}
		
		if (isCommit) {
			// 提交验证码
			SMSSDK.submitVerificationCode("86", mobile, auth_code);
		}
		if(isCodeRight){
			registerRuquest = NoHttp.createStringRequest(Constants.REGISTER, RequestMethod.POST);
			
			registerRuquest.add("mobile", mobile);
			registerRuquest.add("pass", Utils.MD5(password));
			registerRuquest.add("string", Utils.MD5(mobile + Constants.MD5_KEY + Utils.MD5(password)));
			
			CallServer.getInstance().add(this, registerRuquest, callBack, Constants.REGISTER_WHAT, true, false,BaseRequestBean.class);
			
		}
		
	}

	private HttpCallBack<String> callBack = new HttpCallBack<String>() {
	
		public void onSucceed(int what, BaseRequestBean bean) {
			startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
			Toast.show(bean.info);
			finish();
		};
		
		public void onFailed(int what, String errorInfo) {
			Toast.show(errorInfo);
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

					Toast.show(R.string.auth_code_right);
					isCodeRight = true;
					isCommit = false;

				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					// 已经验证
					Toast.show(R.string.auth_code_already);

				}

			} else {

				isCodeRight = false;
				isCommit = true;

				// ((Throwable) data).printStackTrace();
				// Toast.makeText(MainActivity.this, "验证码错误",
				// Toast.LENGTH_SHORT).show();
				// Toast.makeText(MainActivity.this, "123",
				// Toast.LENGTH_SHORT).show();
				int status = 0;
				try {
					((Throwable) data).printStackTrace();
					Throwable throwable = (Throwable) data;

					JSONObject object = new JSONObject(throwable.getMessage());
					String des = object.optString("detail");
					status = object.optInt("status");
					if (!TextUtils.isEmpty(des)) {
						Toast.show(des);
						return;
					}
				} catch (Exception e) {
					SMSLog.getInstance().w(e);
				}
			}

		};
	};
	
	public void finish(View v){
		this.finish();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(registerRuquest != null){
			registerRuquest.cancel();
		}
	};
}
