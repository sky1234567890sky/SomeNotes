package com.example.a1.geeknewschr.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by 1 on 2019/4/15.
 */

public class ZhihuTabAdapter extends FragmentStatePagerAdapter {


    private ArrayList<String> mZhihu_tabtitle;
    private ArrayList<Fragment> mFragments;

    public ZhihuTabAdapter(FragmentManager fm, ArrayList<String> pZhihu_tabtitle, ArrayList<Fragment> pFragments) {
        super(fm);
        mZhihu_tabtitle = pZhihu_tabtitle;
        mFragments = pFragments;
    }

    public ZhihuTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mZhihu_tabtitle.get(position);
    }
}
