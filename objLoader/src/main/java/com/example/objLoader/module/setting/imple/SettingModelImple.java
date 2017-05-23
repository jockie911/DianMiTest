package com.example.objLoader.module.setting.imple;

import android.view.View;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.utils.DataCleanManager;
import com.example.objLoader.wedgit.AlertDialog;

/**
 * Created by yc on 2017/5/23.
 */

public class SettingModelImple<T extends BaseActivity> implements SettingModel {

    private T activity;

    public SettingModelImple(T activity) {
        this.activity = activity;
    }

    @Override
    public void clearChash(final TextView tvClearCash) {
        AlertDialog builder = new AlertDialog(activity).builder();
        builder.setTitle(R.string.clear_cash_title);
        builder.setMsg(R.string.clear_cash_msg);
        builder.setNegativeButton(R.string.cancel,null);
        builder.setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManager.clearAllCache();
                tvClearCash.setText("0K");
            }
        }).show();
    }
}
