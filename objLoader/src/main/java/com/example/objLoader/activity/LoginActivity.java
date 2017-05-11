package com.example.objLoader.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
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
	private boolean isCheckLogin; // 检查是否只是登陆，亦或是未登录跳转过来的

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_login;
	}

	@Override
	protected void initData() {
		tvTitle.setText(R.string.login);
		tvRightTitle.setText(R.string.login_register);
		isCheckLogin = getIntent().getBooleanExtra(IConstant.IS_CHECK_LOGIN, false);
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	@OnClick({R.id.tv_login,R.id.tv_right_title_bar,R.id.tv_forget_pwd,R.id.iv_eye_pwd})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		username = et_username.getText().toString().trim();
		password = et_password.getText().toString().trim();
		switch (v.getId()) {
		case R.id.tv_login:
			login();
			break;
		case R.id.tv_right_title_bar:
			startActivity(false);
			break;
		case R.id.tv_forget_pwd:
			startActivity(true);
			break;
		case R.id.iv_eye_pwd:
			changeEdittextPwdStatus(++ pwdCount);
			break;
		}
	}

	private void startActivity(boolean isForgetPsw){
		Intent intent = new Intent(LoginActivity.this, RegisterAndForgetActivity.class);
		intent.putExtra(IConstant.REGISTER_OR_FORGET,isForgetPsw);
		startActivity(intent);

	}

	private void changeEdittextPwdStatus(int count) {
		ivEyePwd.setImageResource(count % 2 == 1 ? R.drawable.opened_eye:R.drawable.closed_eye);
		et_password.setInputType(count % 2 == 1 ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
				InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		String s = et_password.getText().toString();
		et_password.setSelection(TextUtils.isEmpty(s)? 0 : s.length());
	}

	private void login() {
		if (username.equals("") || password.equals("")) {
			Toast.show(R.string.login_user_pwd_not_null);
			return;
		}
		
		loginRequest = NoHttp.createStringRequest(Constants.LOGIN, RequestMethod.POST);//Constants.LOGIN
		
		loginRequest.add(IConstant.MOBILE, username);
		loginRequest.add(IConstant.PASSWORD, Utils.MD5(password));
		loginRequest.add(IConstant.STRING, Utils.MD5(username + Constants.MD5_KEY + Utils.MD5(password)));
		CallServer.getInstance().add(this, loginRequest, callBack, Constants.REGISTER_WHAT, true, false,BaseRequestBean.class);
	}
	
	private HttpCallBack<BaseRequestBean> callBack = new HttpCallBack<BaseRequestBean>() {
		
		public void onSucceed(int what, BaseRequestBean bean) {
			SharedPreferencesDAO.getInstance(LoginActivity.this).putString(IConstant.MOBILE, username);
			SharedPreferencesDAO.getInstance(LoginActivity.this).putBoolean(IConstant.IS_LOGIN, true);
			Toast.show(bean.info);
			if(!isCheckLogin){
				startActivity(new Intent(LoginActivity.this,AccountInfoActivity.class));
			}
			finish();
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
