package com.example.objLoader.module.setting.presenter;

import android.widget.TextView;

import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.module.setting.imple.SettingModelImple;

/**
 * Created by yc on 2017/5/23.
 */

public class SettingPresent<T extends BaseActivity> {

    private final SettingModelImple settingModelImple;

    public SettingPresent(T activity){
        settingModelImple = new SettingModelImple(activity);
    }


    public void clearCash(TextView tvClearCash) {
        settingModelImple.clearChash(tvClearCash);

    }
}
