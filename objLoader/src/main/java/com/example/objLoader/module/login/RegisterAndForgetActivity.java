package com.example.objLoader.module.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.login.imple.RegistForgetView;
import com.example.objLoader.module.login.presenter.LoginPresent;
import com.example.objLoader.module.login.presenter.RegistForgetPresent;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterAndForgetActivity extends BaseActivity implements RegistForgetView {

	@Bind(R.id.et_mobile)
	EditText et_mobile;
	@Bind(R.id.et_auth_code)
	EditText et_auth_code;
	@Bind(R.id.et_register_password)
	EditText et_register_password;
	@Bind(R.id.tv_send_auth_code)
	TextView tv_send_auth_code;
	@Bind(R.id.iv_eye_pwd)
	ImageView ivEyePwd;
	@Bind(R.id.tv_register)
	TextView tvRegister;

	private boolean isForgetpsw; //true --> forgetPsw / false --> register
	private RegistForgetPresent registForgetPresent;
	private LoginPresent loginPresent;

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
		isForgetpsw = getIntent().getBooleanExtra(IConstant.REGISTER_OR_FORGET, false);
		tvTitle.setText(isForgetpsw?R.string.forget_pwd_find_pwd : R.string.register_title);
		tvRegister.setText(isForgetpsw?R.string.submit:R.string.ok);

		registForgetPresent = new RegistForgetPresent(this);
	}

	@Override
	@OnClick({R.id.tv_register,R.id.tv_send_auth_code,R.id.iv_eye_pwd})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
			case R.id.tv_send_auth_code:
				registForgetPresent.requestSmsCode(tv_send_auth_code);
				break;
			case R.id.tv_register:
				registForgetPresent.registForgetSubmit();
				break;
			case R.id.iv_eye_pwd:
				if(loginPresent == null)
					loginPresent = new LoginPresent();
				loginPresent.changeEdittextPwdStatus(et_register_password,ivEyePwd);
				break;
		}
	}

	@Override
	public String getPhoneNum() {
		return et_mobile.getText().toString().trim();
	}

	@Override
	public String getSmsCode() {
		return et_auth_code.getText().toString().trim();
	}

	@Override
	public String getPws() {
		return et_register_password.getText().toString().trim();
	}

	@Override
	public void finishActivity() {
		finish();
	}

	@Override
	public boolean isForgetPwd() {
		return isForgetpsw;
	}

	@Override
	protected void onDestroy() {
		registForgetPresent.cancel();
		super.onDestroy();
	}
}
