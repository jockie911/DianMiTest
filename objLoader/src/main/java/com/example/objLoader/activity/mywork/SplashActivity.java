package com.example.objLoader.activity.mywork;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.objLoader.R;
import com.example.objLoader.global.BaseActivity;

import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.iv_splash)
    ImageView ivSplash;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean isSwipeBack() {
        return false;
    }

    @Override
    protected void initData() {
        AlphaAnimation aa = new AlphaAnimation(0.5f,1.0f);
        aa.setDuration(1000);
        aa.setRepeatMode(Animation.REVERSE);
        aa.setRepeatCount(2);
        ivSplash.setAnimation(aa);

        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //TODO check out new version to update --> force update/choose update
    }

    @Override
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
    }
}
