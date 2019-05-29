package com.example.zhoutiandemo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 王董辉    class
 */
public class PageAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragments;
    private final ArrayList<String> title;

    public PageAdapter(ArrayList<String> title, ArrayList<Fragment> fragments, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.title = title;
        this.fragments = fragments;

    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
