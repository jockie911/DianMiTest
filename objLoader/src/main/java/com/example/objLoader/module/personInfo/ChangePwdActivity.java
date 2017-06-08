package com.example.objLoader.module.personInfo;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.present.LoginPresent;
import com.example.objLoader.present.ChangePwdPresenter;
import com.example.objLoader.present.view.ChangePwdView;
import com.example.objLoader.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangePwdActivity extends BaseActivity implements ChangePwdView {

	@Bind(R.id.et_old_pwd)
	EditText et_old_pwd;
	@Bind(R.id.et_new_pwd)
	EditText et_new_pwd;
    @Bind(R.id.iv_eye_old_pws)
    ImageView ivEyeOldPwd;
    @Bind(R.id.iv_eye_new_pws)
    ImageView ivEyeNewPwd;

	private ChangePwdPresenter changePwdPresenter;
	private LoginPresent oldLoginPresent;
	private LoginPresent newLoginPresent;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_change_pwd;
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected void initData() {
		tvTitle.setText(R.string.change_pwd_title);
		tvRightTitle.setText(R.string.change_pwd_old_confirm);
	}

	@Override
	protected BasePresenter initPresenter() {
		changePwdPresenter = new ChangePwdPresenter();
		changePwdPresenter.attachView(this);
		return changePwdPresenter;
	}

	@Override
	@OnClick({R.id.tv_right_title_bar,R.id.iv_eye_old_pws,R.id.iv_eye_new_pws})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		switch (v.getId()) {
			case R.id.tv_right_title_bar:
				changePwdPresenter.changechangePwd();
				break;
            case R.id.iv_eye_old_pws:
				if(oldLoginPresent == null)
					oldLoginPresent = new LoginPresent();
				oldLoginPresent.changeEdittextPwdStatus(et_old_pwd,ivEyeOldPwd);
				break;
            case R.id.iv_eye_new_pws:
				if(newLoginPresent == null)
					newLoginPresent = new LoginPresent();
				newLoginPresent.changeEdittextPwdStatus(et_new_pwd,ivEyeNewPwd);
				break;
		}
	}

	@Override
	public String getOldPwd() {
		return et_old_pwd.getText().toString().trim();
	}

	@Override
	public String getNewPwd() {
		return et_new_pwd.getText().toString().trim();
	}

	@Override
	public void onChangePwsSuccess() {
		ToastUtils.show(R.string.edit_pwd_success);
		finish();
	}

	@Override
	public void onChangePwdError(String errerInfo) {
		ToastUtils.show(errerInfo);
	}
}
