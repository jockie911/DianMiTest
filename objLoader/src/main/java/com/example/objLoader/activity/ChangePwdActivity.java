package com.example.objLoader.activity;

import com.example.objLoader.R;
import com.example.objLoader.bean.BaseRequestBean;
import com.example.objLoader.global.AbActivityManager;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.nohttp.CallServer;
import com.example.objLoader.nohttp.HttpCallBack;
import com.example.objLoader.utils.Constants;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.Toast;
import com.example.objLoader.utils.Utils;
import com.example.objLoader.HomeActivity;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangePwdActivity extends BaseActivity{

	@Bind(R.id.et_old_pwd)
	EditText et_old_pwd;
	@Bind(R.id.et_new_pwd)
	EditText et_new_pwd;
    @Bind(R.id.iv_eye_old_pws)
    ImageView ivEyeOldPwd;
    @Bind(R.id.iv_eye_new_pws)
    ImageView ivEyeNewPwd;
    // count 计数器
    private int oldEyeCount;
    private int newEyeCount;


	private Request<String> changePwdRequest;
	private String mobile,old_pwd,new_pwd;
	

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
	@OnClick({R.id.tv_right_title_bar,R.id.iv_eye_old_pws,R.id.iv_eye_new_pws})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		mobile = SharedPreferencesDAO.getInstance(mContext).getString("phone_number");
		old_pwd = et_old_pwd.getText().toString().trim();
		new_pwd = et_new_pwd.getText().toString().trim();

		switch (v.getId()) {
			case R.id.tv_title_right:
				changePwd();
				break;
            case R.id.iv_eye_old_pws:
                changeEdittextInputType(++oldEyeCount,et_old_pwd,ivEyeOldPwd);
                break;
            case R.id.iv_eye_new_pws:
                changeEdittextInputType(++newEyeCount,et_new_pwd,ivEyeNewPwd);
                break;
		}
	}

	//改变edittext 输入类型
    private void changeEdittextInputType(int count, EditText et,ImageView targetImageView) {
        if(count % 2 == 1){ // 可见状态
            targetImageView.setImageResource(R.drawable.opened_eye);
            et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            targetImageView.setImageResource(R.drawable.closed_eye);
            et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        if(!TextUtils.isEmpty(et.getText().toString().trim())){
			et.setSelection(et.getText().length());
		}
    }

    private void changePwd() {
		if(old_pwd.length() < 5 || new_pwd.length() < 5){
			Toast.show(R.string.pwd_lenght);
			return;
		}
		changePwdRequest = NoHttp.createStringRequest(Constants.CHANGE_PWD, RequestMethod.POST);
		changePwdRequest.add(IConstant.MOBILE, mobile);
		changePwdRequest.add(IConstant.PASSWORD, Utils.MD5(old_pwd));
		changePwdRequest.add(IConstant.PASSWORD_NEW,Utils.MD5(new_pwd));
		changePwdRequest.add(IConstant.STRING,Utils.MD5(Utils.MD5(new_pwd) + Utils.MD5(mobile + Constants.MD5_KEY + Utils.MD5(old_pwd))));
		CallServer.getInstance().add(mContext, changePwdRequest, callBack, Constants.CHANGE_PWD_WHAT, true, false,BaseRequestBean.class);
	}
	
	private HttpCallBack<BaseRequestBean> callBack = new HttpCallBack<BaseRequestBean>() {
	
		public void onSucceed(int what, BaseRequestBean bean) {
			Toast.show(bean.info);
			startActivity(new Intent(mContext, LoginActivity.class));
			finish();
		};
		
		public void onFailed(int what, String errorInfo) {
			Toast.show(errorInfo);
		};
	};
}
