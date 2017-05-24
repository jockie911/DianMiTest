package com.example.objLoader.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.objLoader.istatic.IConstant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by jockie on 2016/7/8.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected Context mContext;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = BaseApp.getContext();
        rootView = inflater(inflater);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getFragment();
        initData();
    }

    protected abstract View inflater(LayoutInflater inflater);

    protected abstract void initData();

    protected boolean isUseButterKnife(){
        return true;
    }

    @Override
    public void onDestroy() {
        if(isUseButterKnife())
            ButterKnife.unbind(this);
        super.onDestroy();
    }

//    protected void replaceFragment(Fragment fm){
//        getChildFragmentManager().beginTransaction().replace(R.id.content_container,fm).commit();
//    }

    protected int currentSelectedPosition = 0;
    protected int beforeSelectedPosition = -1;
    /**
     * 对于有fragment切换的  hide和show
     */
//    protected void dealWithFragment() {
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        if (currentSelectedPosition == beforeSelectedPosition) {
//            return;
//        }
//        Fragment baseFragment = mFragment.get(currentSelectedPosition);
//        if (baseFragment.isAdded()) {
//            transaction.show(baseFragment);
//        } else {
//            transaction.add(R.id.content_container, baseFragment);
//        }
//        if (beforeSelectedPosition != -1) {
//            transaction.hide(mFragment.get(beforeSelectedPosition));
//        }
//        transaction.commit();
//    }

    protected List<Fragment> mFragment = new ArrayList<>();
    protected List<Fragment> getFragment(){
        return mFragment;
    }

    protected boolean isDoubleClick(View v){
        Object tag = v.getTag(v.getId());
        long beforeTimemiles = tag != null ? (long) tag : 0;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        v.setTag(v.getId(),timeInMillis);
        return timeInMillis - beforeTimemiles < IConstant.NO_DOUBLE_CLICK_TIME;
    }
}
