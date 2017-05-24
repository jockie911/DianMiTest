package com.example.objLoader.utils;

import android.content.Intent;

import com.example.objLoader.module.login.LoginActivity;
import com.example.objLoader.base.BaseApp;
import com.example.objLoader.istatic.IConstant;

/**
 * Created by yc on 2017/5/8.
 */

public class LoginUtils {

    /**
     * @param isCheckLogin false-->just start this activity
     * @return
     */
    public static boolean isLogin(Boolean... isCheckLogin){
        boolean aBoolean = SPUtils.getInstance(BaseApp.getContext()).getBoolean(IConstant.IS_LOGIN);
        if(!aBoolean){
            Intent intent = new Intent(BaseApp.getContext(), LoginActivity.class);
            if(isCheckLogin.length > 0)
            intent.putExtra(IConstant.IS_CHECK_LOGIN,!(isCheckLogin.length > 0 && !isCheckLogin[0]));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApp.getContext().startActivity(intent);
            return false;
        }
        return true;
    }
}
