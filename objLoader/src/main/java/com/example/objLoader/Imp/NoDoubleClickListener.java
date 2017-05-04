package com.example.objLoader.Imp;

import android.view.View;

import java.util.Calendar;

/**
 * Created by yc on 2017/5/4.
 */
public abstract class  NoDoubleClickListener implements View.OnClickListener {

public static final int MIN_CLICK_DELAY_TIME = 1000;
private long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
            }
        }

        public abstract void onNoDoubleClick(View view);
}
