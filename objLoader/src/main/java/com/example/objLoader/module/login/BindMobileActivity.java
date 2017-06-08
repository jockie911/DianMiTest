package com.example.objLoader.module.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BasePresenter;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.present.LoginPresent;
import com.example.objLoader.present.RegistForgetPresent;
import com.example.objLoader.present.view.IRegistForgetView;

import butterknife.Bind;
import butterknife.OnClick;

public class BindMobileActivity extends BaseActivity implements IRegistForgetView {

    @Bind(R.id.et_mobile)
    EditText et_mobile;
    @Bind(R.id.et_auth_code)
    EditText et_auth_code;
    @Bind(R.id.tv_send_auth_code)
    TextView tv_send_auth_code;
    @Bind(R.id.tv_register)
    TextView tvRegister;
    private LoginPresent loginPresent;
    private RegistForgetPresent registForgetPresent;

    @Override
    protected boolean isHavaBaseTitleBar() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_checkout_mobile;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.bind_mobile);
        tvRegister.setText(R.string.submit);
    }

    @Override
    protected BasePresenter initPresenter() {
        registForgetPresent = new RegistForgetPresent(this);
        registForgetPresent.attachView(this);
        return registForgetPresent;
    }

    @Override
    @OnClick({R.id.tv_register,R.id.tv_send_auth_code})
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
        switch (v.getId()) {
            case R.id.tv_send_auth_code:
                if(registForgetPresent == null)
                    registForgetPresent = new RegistForgetPresent(this);
                registForgetPresent.requestSmsCode(tv_send_auth_code);
                break;
            case R.id.tv_register:
                registForgetPresent.bindMobile();
                break;
        }
    }

    @Override
    public String getPhoneNum() {
        return et_mobile.getText().toString();
    }

    @Override
    public String getSmsCode() {
        return et_auth_code.getText().toString();
    }

    /**
     * 返回的是微信的code
     * @return
     */
    @Override
    public String getPws() {
        return getIntent().getStringExtra(IConstant.CODE);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public boolean isForgetPwd() {
        return false;
    }

    @Override
    public boolean isBindMobile() {
        return true;
    }
}
