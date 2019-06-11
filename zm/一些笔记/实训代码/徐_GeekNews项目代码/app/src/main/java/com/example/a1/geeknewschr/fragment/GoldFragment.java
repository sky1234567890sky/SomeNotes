package com.example.a1.geeknewschr.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.activity.ShowActivity;
import com.example.a1.geeknewschr.base.BaseFragment;
import com.example.a1.geeknewschr.base.Constants;
import com.example.a1.geeknewschr.bean.GoldShowBean;
import com.example.a1.geeknewschr.persenter.GoldP;
import com.example.a1.geeknewschr.view.GoldV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends BaseFragment<GoldV, GoldP> {


    @BindView(R.id.tab_gold)
    TabLayout mTabGold;
    @BindView(R.id.img_gold)
    ImageView mImgGold;
    @BindView(R.id.vp_gold)
    ViewPager mVpGold;
    Unbinder unbinder;
    private ArrayList<Fragment> mFragments;
    private ArrayList<GoldShowBean> mList;

    public GoldFragment() {
        // Required empty public constructor
    }


    @Override
    protected GoldP initPersenter() {
        return new GoldP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        initTitle();
        setFragments();
    }

    private void setFragments() {
        initFragment();
        VpGoldAdapter vpGoldAdapter = new VpGoldAdapter(getChildFragmentManager(), mList, mFragments);
        mVpGold.setAdapter(vpGoldAdapter);
        mTabGold.setupWithViewPager(mVpGold);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            GoldShowBean goldShowBean = mList.get(i);
            if (goldShowBean.isChecked) {
                mFragments.add(GoldDetailFragment.newInstance(goldShowBean.title));
            }
        }
    }

    private void initTitle() {
        mList = new ArrayList<>();
        mList.add(new GoldShowBean("工具资源", true));
        mList.add(new GoldShowBean("Android", true));
        mList.add(new GoldShowBean("iOS", true));
        mList.add(new GoldShowBean("设计", true));
        mList.add(new GoldShowBean("产品", true));
        mList.add(new GoldShowBean("阅读", true));
        mList.add(new GoldShowBean("前端", true));
        mList.add(new GoldShowBean("后端", true));
    }
    @OnClick({R.id.img_gold})
    public void click(View v){
        switch (v.getId()){
            case R.id.img_gold:
                go2ShowActivity();
                break;
        }
    }
    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA,mList);
        startActivityForResult(intent,100);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                mList = (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
                //刷新界面
                setFragments();
            }
        }
    }
}
