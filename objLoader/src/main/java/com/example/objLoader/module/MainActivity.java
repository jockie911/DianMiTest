package com.example.objLoader.module;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.objLoader.R;
import com.example.objLoader.adapter.DiscreteScrollViewAdapter;
import com.example.objLoader.global.BaseActivity;
import com.example.objLoader.istatic.IConstant;
import com.example.objLoader.module.login.LoginActivity;
import com.example.objLoader.module.measure.FrontPicActivity;
import com.example.objLoader.module.personInfo.AccountInfoActivity;
import com.example.objLoader.utils.SPUtils;
import com.example.objLoader.utils.ToastUtils;
import com.example.objLoader.utils.DensityUtil;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rel_person_center)
    RelativeLayout rel_person_center;
    @Bind(R.id.tv_start_measure)
    TextView tvStartMeasure;
    @Bind(R.id.scrollview)
    DiscreteScrollView discreteScrollView;
    @Bind(R.id.rel_start_measure)
    RelativeLayout relStartmeasure;
    private long exitTime;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main2;
    }

    @Override
    protected boolean isSwipeBack() {
        return false;
    }

    @Override
    protected void initData() {
        requestPermissions();
        rel_person_center.setOnClickListener(this);

        int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        int realWidth = width - DensityUtil.dip2px(this, 60);
        ViewGroup.LayoutParams layoutParams = tvStartMeasure.getLayoutParams();
        layoutParams.width = realWidth;
        tvStartMeasure.setLayoutParams(layoutParams);
        discreteScrollView.setAdapter(new DiscreteScrollViewAdapter(realWidth));
        discreteScrollView.setItemTransitionTimeMillis(100);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.7f)
                .build());

        discreteScrollView.setScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
                setButtonStartBgInvalid(false);
            }

            @Override
            public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
                setButtonStartStatus(adapterPosition);
            }

            @Override
            public void onScroll(float scrollPosition, @NonNull RecyclerView.ViewHolder currentHolder, @NonNull RecyclerView.ViewHolder newCurrent) {

            }
        });
    }

    /**
     * position == 0 ? 女 ：男
     * @param position
     */
    public void setButtonStartStatus(int position){
        if(position == 0){
            setButtonStartBgInvalid(true);
            tvStartMeasure.setText(R.string.begin_measure);
        }else{
            setButtonStartBgInvalid(false);
            tvStartMeasure.setText(R.string.no_present);
        }
    }

    public void setButtonStartBgInvalid(boolean invalid){
        relStartmeasure.setBackgroundResource(invalid?R.drawable.start_button : R.drawable.start_button_invalid);
        relStartmeasure.setClickable(invalid);
    }

    @Override
    @OnClick(R.id.rel_start_measure)
    public void onClick(View v) {
        if(isDoubleClick(v)) return;
        switch (v.getId()){
            case R.id.rel_start_measure:
                Intent intent = new Intent(MainActivity.this, FrontPicActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_person_center:
                if(SPUtils.getInstance(MainActivity.this).getBoolean(IConstant.IS_LOGIN)){
                    startActivity(new Intent(MainActivity.this, AccountInfoActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.show(R.string.double_click_exit);
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
