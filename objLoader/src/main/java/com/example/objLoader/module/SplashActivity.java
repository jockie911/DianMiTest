package com.example.objLoader.module;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.objLoader.R;
import com.example.objLoader.base.BaseActivity;

import butterknife.Bind;

public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    @Bind(R.id.iv_splash)
    ImageView ivSplash;
    private AnimationSet animationSet;

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
        animationSet = new AnimationSet(true);
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                AlphaAnimation aa = new AlphaAnimation(1.0f,0.0f);
//                aa.setRepeatMode(Animation.REVERSE);


                ScaleAnimation sa = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                animationSet.setDuration(1200);
//                animationSet.addAnimation(aa);
                animationSet.addAnimation(sa);
                animationSet.setInterpolator(new AccelerateInterpolator());
                animationSet.setFillAfter(true);

                ivSplash.startAnimation(animationSet);
            }
        },2000);
        animationSet.setAnimationListener(this);
        //TODO check out new version to update --> force update/choose update
    }

    @Override
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
    }


    static Handler sHandler = new Handler();

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
}
