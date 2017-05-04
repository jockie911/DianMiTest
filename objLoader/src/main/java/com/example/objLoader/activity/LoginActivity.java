package com.example.objLoader.activity;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.Constants;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.Utils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

	@Bind(R.id.et_username)
	EditText et_username;
	@Bind(R.id.et_password)
	EditText et_password;
	@Bind(R.id.tv_forget_pwd)
	TextView tv_forget_pwd;
	@Bind(R.id.iv_eye_pwd)
	ImageView ivEyePwd;

	private String username,password;
	private Request<String> loginRequest;
	private int pwdCount;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_login;
	}

	@Override
	protected void initData() {
		SharedPreferencesDAO instance = SharedPreferencesDAO.getInstance(this);
		tvTitle.setText(R.string.login);
		tvRightTitle.setText(R.string.login_register);
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	@OnClick({R.id.tv_login,R.id.tv_right_title_bar,R.id.tv_forget_pwd,R.id.iv_eye_pwd})
	public void onClick(View v) {
		
		username = et_username.getText().toString().trim();
		password = et_password.getText().toString().trim();
		switch (v.getId()) {
		case R.id.tv_login:
			login();
			break;
		case R.id.tv_right_title_bar:
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			break;
		case R.id.tv_forget_pwd:
			startActivity(new Intent(LoginActivity.this, ForgertPwdActivity.class));
			break;
		case R.id.iv_eye_pwd:
			changeEdittextPwdStatus(++ pwdCount);
			break;
		}
	}

	private void changeEdittextPwdStatus(int count) {
		ivEyePwd.setImageResource(count % 2 == 0 ? R.drawable.opened_eye:R.drawable.closed_eye);
		et_password.setInputType(count % 2 == 0 ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
				InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}

	private void login() {
		if (username.equals("") || password.equals("")) {
			Toast.show(R.string.login_user_pwd_not_null);
			return;
		}
		
		loginRequest = NoHttp.createStringRequest(Constants.LOGIN, RequestMethod.POST);//Constants.LOGIN
		
		loginRequest.add("mobile", username);
		loginRequest.add("pass", Utils.MD5(password));
		loginRequest.add("string", Utils.MD5(username + Constants.MD5_KEY + Utils.MD5(password)));
		CallServer.getInstance().add(this, loginRequest, callBack, Constants.REGISTER_WHAT, true, false,BaseRequestBean.class);
	}
	
	private HttpCallBack<String> callBack = new HttpCallBack<String>() {
		
		public void onSucceed(int what, BaseRequestBean bean) {
			SharedPreferencesDAO.getInstance(LoginActivity.this).putString("phone_number", username);
			SharedPreferencesDAO.getInstance(LoginActivity.this).putBoolean("isLogin", true);
			Toast.show(bean.info);
			startActivity(new Intent(LoginActivity.this,AccountInfoActivity.class));
			finish();
		};
		
		public void onSucceed(int what, com.yolanda.nohttp.rest.Response<String> response) {
		};
		
		public void onFailed(int what, String errorInfo) {
			Toast.show(errorInfo);
		};
	};
	@Override
	protected void onDestroy() {
		super.onDestroy();	
		if(loginRequest != null){
			loginRequest.cancel();
		}
	};

}
