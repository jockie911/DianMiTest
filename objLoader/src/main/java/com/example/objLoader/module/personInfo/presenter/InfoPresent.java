package com.example.objLoader.module.personInfo.presenter;

import android.content.Intent;
import android.view.View;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.login.LoginActivity;
import com.example.objLoader.module.personInfo.AccountInfoActivity;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.wedgit.AlertDialog;

/**
 * Created by yc on 2017/5/22.
 */

public class InfoPresent {

    public InfoPresent(){

    }

    public void logout(final AccountInfoActivity activity){
        final AlertDialog builder = new AlertDialog(activity).builder();
        builder.setTitle(R.string.logout);
        builder.setMsg(R.string.logout_msg);
        builder.setNegativeButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.getInstance().putString(IConstant.USERNAME, "");
                SPUtils.getInstance().putBoolean(IConstant.IS_LOGIN, false);
                Intent intent = new Intent(BaseApp.getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
                activity.finish();
            }
        }).show();
    }
}
