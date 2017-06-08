package com.example.objLoader.module;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;
import com.example.objLoader.module.setting.SettingActivity;

import java.lang.ref.WeakReference;

import butterknife.Bind;

public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    @Bind(R.id.iv_splash)
    ImageView ivSplash;
    private AnimationSet animationSet;

    private MyHandler sHandler = new MyHandler(this);

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

        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationSet = new AnimationSet(true);
                animationSet.setAnimationListener(SplashActivity.this);

                ScaleAnimation sa = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animationSet.setDuration(1200);
        //                animationSet.addAnimation(aa);
                animationSet.addAnimation(sa);
                animationSet.setInterpolator(new AccelerateInterpolator());
                animationSet.setFillAfter(true);
                ivSplash.startAnimation(animationSet);
            }
        },2000);
        //TODO check out new version to update --> force update/choose update
    }

    @Override
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
    }


    @Override
    protected void onDestroy() {
        if(animationSet != null)
            animationSet.cancel();
        sHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

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

    private static class MyHandler extends Handler {
        private final WeakReference<SplashActivity> mActivity;

        public MyHandler(SplashActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
}
