package com.example.objLoader.module.personInfo.imple;

import android.app.Activity;

/**
 * Created by yc on 2017/5/22.
 */

public interface InfoModel<T extends Activity> {

    void logout(T activity);

}
