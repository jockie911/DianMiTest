package com.example.objLoader.present;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.utils.DataCleanManager;
import com.example.objLoader.utils.MySnackbar;
import com.example.objLoader.wedgit.AlertDialog;

/**
 * Created by yc on 2017/5/23.
 */

public class SettingPresent<T extends BaseActivity> {

    private final T activity;

    public SettingPresent(T activity){
        this.activity = activity;
    }

    public void clearCash(final TextView tvClearCash) {
        if(TextUtils.equals("0K",tvClearCash.getText().toString())){
            MySnackbar.makeSnackBarGreen(tvClearCash, BaseApp.getContext().getResources().getString(R.string.current_no_cash));
            return;
        }
        AlertDialog builder = new AlertDialog(activity).builder();
        builder.setTitle(R.string.clear_cash_title);
        builder.setMsg(R.string.clear_cash_msg);
        builder.setNegativeButton(R.string.cancel,null);
        builder.setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManager.clearAllCache();
                tvClearCash.setText("0K");
                MySnackbar.makeSnackBarGreen(tvClearCash,BaseApp.getContext().getResources().getString(R.string.clear_cash_complete));
            }
        }).show();
    }
}
