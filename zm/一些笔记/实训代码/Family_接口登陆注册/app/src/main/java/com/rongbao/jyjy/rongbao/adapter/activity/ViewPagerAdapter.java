package com.rongbao.jyjy.rongbao.adapter.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rongbao.jyjy.rongbao.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/4/24 0024.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private MainActivity context;
    private final ArrayList<Fragment> fragments;
    private final ArrayList<Integer> titles;

    public ViewPagerAdapter(MainActivity mainActivity, FragmentManager supportFragmentManager, ArrayList<Fragment> fragments, ArrayList<Integer> titles) {
        super(supportFragmentManager);
        this.context = mainActivity;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(titles.get(position)) ;
    }
}
