package com.example.objLoader.module.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.personInfo.AccountInfoActivity;
import com.example.objLoader.module.login.imple.LoginView;
import com.example.objLoader.module.login.presenter.LoginPresent;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

	@Bind(R.id.et_username)
	EditText et_username;
	@Bind(R.id.et_password)
	EditText et_password;
	@Bind(R.id.tv_forget_pwd)
	TextView tv_forget_pwd;
	@Bind(R.id.iv_eye_pwd)
	ImageView ivEyePwd;

	private LoginPresent loginPresent;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_login;
	}

	@Override
	protected void initData() {
		tvTitle.setText(R.string.login);
		tvRightTitle.setText(R.string.login_register);
		loginPresent = new LoginPresent(this);
		et_username.requestFocus();
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	@OnClick({R.id.tv_login,R.id.tv_right_title_bar,R.id.tv_forget_pwd,R.id.iv_eye_pwd,R.id.iv_login_weixin})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
		case R.id.tv_login:
			loginPresent.login();
			break;
		case R.id.tv_right_title_bar:
            moveToRegistActivity();
			break;
		case R.id.tv_forget_pwd:
		    moveToForgetActivity();
			break;
		case R.id.iv_eye_pwd:
		    loginPresent.changeEdittextPwdStatus(et_password,ivEyePwd);
			break;
		case R.id.iv_login_weixin:
			loginPresent.loginWX();
			break;
		}
	}

	@Override
	public String getPhoneNum() {
		return et_username.getText().toString().trim();
	}

	@Override
	public String getPwd() {
		return et_password.getText().toString().trim();
	}

	public void moveToRegistActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterAndForgetActivity.class);
        intent.putExtra(IConstant.REGISTER_OR_FORGET,false);
        startActivity(intent);
	}

	public void moveToForgetActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterAndForgetActivity.class);
        intent.putExtra(IConstant.REGISTER_OR_FORGET,true);
        startActivity(intent);
	}

	@Override
	public void moveToAccountInfoActivity() {
		startActivity(new Intent(LoginActivity.this,AccountInfoActivity.class));
	}

	@Override
	public void finishActivity() {
		finish();
	}

	@Override
	public boolean isCheckLogin() {
		return getIntent().getBooleanExtra(IConstant.IS_CHECK_LOGIN, false);
	}
}
