package com.example.objLoader.activity;

import android.view.View;
import android.widget.EditText;

import com.example.objLoader.R;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.utils.SharedPreferencesDAO;
import com.example.objLoader.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangeUsernameActivity extends BaseActivity {

	@Bind(R.id.et_change_username)
	EditText et_change_username;
	private String username;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_change_username;
	}

	@Override
	protected boolean isHavaBaseTitleBar() {
		return true;
	}

	@Override
	protected void initData() {
		tvTitle.setText(R.string.change_username_title);
		tvRightTitle.setText(R.string.save);
	}

	@Override
	@OnClick({R.id.tv_right_title_bar,R.id.iv_clear})
	public void onClick(View v) {
		if(isDoubleClick(v)) return;
		username = et_change_username.getText().toString().trim();
		
		switch (v.getId()) {
		case R.id.tv_right_title_bar:
			changeUsername();
			break;
		case R.id.iv_clear:
			et_change_username.setText("");
			break;
		}

	}
	
	//TODO send change nickname To network
	private void changeUsername() {
		if(username.length() <= 0){
			ToastUtils.show(R.string.input_username);
			return;
		}
		SharedPreferencesDAO.getInstance(mContext).putString("username", username);
		ToastUtils.show(R.string.username_save);
		finish();
		
	}
}
