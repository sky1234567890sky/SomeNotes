package com.example.a1.geeknewschr.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.VpGankAdapter;
import com.example.a1.geeknewschr.fragment.gank.AndroidFragment;
import com.example.a1.geeknewschr.fragment.gank.FuliFragment;
import com.example.a1.geeknewschr.fragment.gank.IsoFragment;
import com.example.a1.geeknewschr.fragment.gank.QianDuanFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends Fragment {


    @BindView(R.id.tab_gank)
    TabLayout mTabGank;
    @BindView(R.id.vp_gank)
    ViewPager mVpGank;
    Unbinder unbinder;

    public GankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gank, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        ArrayList<String> title = new ArrayList<>();
        title.add("ANDROID");
        title.add("iSO");
        title.add("前端");
        title.add("福利");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AndroidFragment());
        fragments.add(new IsoFragment());
        fragments.add(new QianDuanFragment());
        fragments.add(new FuliFragment());
        VpGankAdapter vpGankAdapter = new VpGankAdapter(getChildFragmentManager(), title, fragments);
        mVpGank.setAdapter(vpGankAdapter);
        mTabGank.setupWithViewPager(mVpGank);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
