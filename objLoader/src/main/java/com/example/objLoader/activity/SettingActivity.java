package com.example.objLoader.activity;

import android.view.View;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.wedgit.AlertDialog;
import com.example.objLoader.utils.DataCleanManager;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.tv_clear_cash)
    TextView tvClearCash;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected boolean isHavaBaseTitleBar() {
        return true;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.setting);
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize();
            tvClearCash.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @OnClick({R.id.rel_clear_cash})
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
        switch (v.getId()){
            case R.id.rel_clear_cash:
                showClearCashDialog();
                break;
        }
    }

    private void showClearCashDialog() {
        AlertDialog builder = new AlertDialog(this).builder();
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
