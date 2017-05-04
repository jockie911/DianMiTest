package com.example.objLoader.global;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by jockie on 2016/7/8.
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = BaseApp.getContext();
        View view = inflater(inflater);
        ButterKnife.bind(this,view);
        return view;
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
}
