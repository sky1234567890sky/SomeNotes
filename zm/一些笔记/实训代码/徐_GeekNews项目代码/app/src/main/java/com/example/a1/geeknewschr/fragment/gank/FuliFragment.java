package com.example.a1.geeknewschr.fragment.gank;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.RlFuliAdapter;
import com.example.a1.geeknewschr.base.BaseFragment;
import com.example.a1.geeknewschr.bean.gank.FuliBean;
import com.example.a1.geeknewschr.persenter.gank.FuliP;
import com.example.a1.geeknewschr.view.gank.FuliView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuliFragment extends BaseFragment<FuliView, FuliP> implements FuliView {


    private static final String TAG = "fuli";
    @BindView(R.id.rl_fl)
    RecyclerView mRlFl;
    Unbinder unbinder;
    @BindView(R.id.sl)
    SmartRefreshLayout mSl;
    Unbinder unbinder1;
    private int page=1;
    private ArrayList<FuliBean.ResultsBean> mFuliData;
    private RlFuliAdapter mRlFuliAdapter;

    public FuliFragment() {
        // Required empty public constructor
    }

    @Override
    protected FuliP initPersenter() {
        return new FuliP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fuli;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRlFl.setLayoutManager(manager);
        //防止滑动时乱动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mFuliData = new ArrayList<>();
        mRlFuliAdapter = new RlFuliAdapter(mFuliData, getActivity());
        mRlFl.setAdapter(mRlFuliAdapter);

        mSl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mFuliData.clear();
                initData();

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPersenter.getFuliData(page);
    }
 

    @Override
    public void onFuliSuccess(FuliBean pFuliBean) {
        List<FuliBean.ResultsBean> results = pFuliBean.getResults();
        Log.i(TAG, "onFuliSuccess: "+results);
        mFuliData.addAll(results);
        setImageScale();
//        mRlFuliAdapter.setFuliData(mFuliData);
//        mRlFuliAdapter.notifyDataSetChanged();
    }
    private void setImageScale() {
        for (final FuliBean.ResultsBean bean :mFuliData) {
            if (bean.getScale() == 0) {
                //未计算过宽高比
                Glide.with(getActivity()).load(bean.getUrl()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {
                        //宽高比
                        float scale = resource.getIntrinsicWidth() * 1.0f / resource.getIntrinsicHeight();
                        bean.setScale(scale);
                        mRlFuliAdapter.notifyDataSetChanged();
                    }
                });
            }else {
                mRlFuliAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFuliFail(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
 
}
