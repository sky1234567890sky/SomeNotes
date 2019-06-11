package com.example.a1.geeknewschr.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 1 on 2019/4/22.
 */

public class VpGankAdapter extends FragmentPagerAdapter {

    private ArrayList<String> mTitle_gank;
    private ArrayList<Fragment> mFragments_gank;

    public VpGankAdapter(FragmentManager fm, ArrayList<String> pTitle_gank, ArrayList<Fragment> pFragments_gank) {
        super(fm);
        mTitle_gank = pTitle_gank;
        mFragments_gank = pFragments_gank;
    }

    public VpGankAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments_gank.get(position);
    }

    @Override
    public int getCount() {
        return mFragments_gank.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle_gank.get(position);
    }
}
