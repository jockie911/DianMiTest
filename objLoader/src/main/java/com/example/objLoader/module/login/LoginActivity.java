package com.example.objLoader.module.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.bean.event.WxLoginSuccessEvent;
import com.example.objLoader.istatic.Constants;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.present.view.ILoginView;
import com.example.objLoader.present.LoginPresent;
import com.example.objLoader.module.personInfo.AccountInfoActivity;
import com.example.objLoader.utils.SPUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

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
		et_username.requestFocus();
		if(!EventBus.getDefault().isRegistered(this))
			EventBus.getDefault().register(this);
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected BasePresenter initPresenter() {
		loginPresent = new LoginPresent(this);
		loginPresent.attachView(this);
		return loginPresent;
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
			SendAuth.Req req = new SendAuth.Req();
			req.scope = IConstant.WX_REQ_SCOPE;
			req.state = IConstant.WX_REQ_STATE;
			IWXAPI wxapi = WXAPIFactory.createWXAPI(this, Constants.WX_ID,true);
			wxapi.sendReq(req);
//			loginPresent.loginWX();
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

	@Override
	protected void onDestroy() {
		if(EventBus.getDefault().isRegistered(this))
			EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void wxLoginSuccess(WxLoginSuccessEvent env){
		SPUtils.getInstance().putBoolean(IConstant.IS_LOGIN,true);
		startActivity(new Intent(this, AccountInfoActivity.class));
		finish();
	}
}
