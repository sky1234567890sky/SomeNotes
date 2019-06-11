package com.example.a1.geeknewschr.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.example.a1.geeknewschr.bean.GoldShowBean;

import java.util.ArrayList;

/**
 * Created by 1 on 2019/4/19.
 */

public class VpGoldAdapter extends FragmentStatePagerAdapter {

    private ArrayList<GoldShowBean> mMList;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mNewTitles = new ArrayList<>();

    public VpGoldAdapter(FragmentManager fm, ArrayList<GoldShowBean> pMList, ArrayList<Fragment> pFragments) {
        super(fm);
        mMList = pMList;
        mFragments = pFragments;
        for (int i = 0; i < mMList.size(); i++) {
            GoldShowBean goldShowBean = mMList.get(i);
            if (goldShowBean.isChecked){
                mNewTitles.add(goldShowBean.title);
            }
        }
    }

    public VpGoldAdapter(FragmentManager fm) {
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
        return mNewTitles.get(position);
    }
}
