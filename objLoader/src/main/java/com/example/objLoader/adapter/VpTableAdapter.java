package com.example.objLoader.adapter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jockie on 2016/8/11.
 */
public class VpTableAdapter extends FragmentPagerAdapter{
    private List<String> tabTitleLists;
    private List<Fragment> fmLists;

    public VpTableAdapter(FragmentManager fm, List<Fragment> fmLists, List<String> tabTitleLists, TabLayout tableLayout) {
        super(fm);
        this.fmLists = fmLists;
        this.tabTitleLists = tabTitleLists;
        if(tableLayout != null){
            for(String s : tabTitleLists)
                tableLayout.addTab(tableLayout.newTab().setText(s));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fmLists.get(position);
    }

    @Override
    public int getCount() {
        return fmLists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleLists == null? null : tabTitleLists.get(position % tabTitleLists.size());
    }

//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }
}
