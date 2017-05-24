package com.example.objLoader.module.setting;

import android.view.View;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.module.setting.presenter.SettingPresent;
import com.example.objLoader.utils.DataCleanManager;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.tv_clear_cash)
    TextView tvClearCash;
    private SettingPresent settingPresent;

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
        settingPresent = new SettingPresent(this);
    }

    @Override
    @OnClick({R.id.rel_clear_cash})
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
        switch (v.getId()){
            case R.id.rel_clear_cash:
                settingPresent.clearCash(tvClearCash);
                break;
        }
    }
}
