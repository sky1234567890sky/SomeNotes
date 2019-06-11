package com.example.a1.geeknewschr.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.ZhihuTabAdapter;
import com.example.a1.geeknewschr.fragment.zhihu.Zhihu_hot_Fragment;
import com.example.a1.geeknewschr.fragment.zhihu.Zhihu_news_Fragment;
import com.example.a1.geeknewschr.fragment.zhihu.Zhihu_special_column_Fragment;
import com.example.a1.geeknewschr.fragment.zhihu.Zhihu_theam_Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuFragment extends Fragment {


    @BindView(R.id.tab_zhihu)
    TabLayout mTabZhihu;
    @BindView(R.id.vp_zhihu)
    ViewPager mVpZhihu;
    Unbinder unbinder;
    private ArrayList<String> mZhuhu_tab;
    private ArrayList<Fragment> mFragments;

    public ZhihuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zhihu, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData_tab();
        return view;
    }

    private void initData_tab() {
        mZhuhu_tab = new ArrayList<>();
        mZhuhu_tab.add("日报");
        mZhuhu_tab.add("主题");
        mZhuhu_tab.add("专栏");
        mZhuhu_tab.add("热门");
        mFragments = new ArrayList<>();
        mFragments.add(new Zhihu_news_Fragment());
        mFragments.add(new Zhihu_theam_Fragment());
        mFragments.add(new Zhihu_special_column_Fragment());
        mFragments.add(new Zhihu_hot_Fragment());
        ZhihuTabAdapter zhihuTabAdapter = new ZhihuTabAdapter(getChildFragmentManager(), mZhuhu_tab, mFragments);
        mVpZhihu.setAdapter(zhihuTabAdapter);
        mTabZhihu.setupWithViewPager(mVpZhihu);
    }

}
