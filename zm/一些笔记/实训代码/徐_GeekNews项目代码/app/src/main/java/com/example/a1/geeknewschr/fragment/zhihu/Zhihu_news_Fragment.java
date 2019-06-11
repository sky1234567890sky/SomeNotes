package com.example.a1.geeknewschr.fragment.zhihu;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.activity.CalenderActivity;
import com.example.a1.geeknewschr.adapter.RlZhNewsAdapter;
import com.example.a1.geeknewschr.base.BaseFragment;
import com.example.a1.geeknewschr.bean.DialyNewsBean;
import com.example.a1.geeknewschr.persenter.zhihu.ZhihuNewsP;
import com.example.a1.geeknewschr.view.zhihu.ZhihuNewsV;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Zhihu_news_Fragment extends BaseFragment<ZhihuNewsV, ZhihuNewsP> implements ZhihuNewsV {


    private static final String TAG = "zhnews";

    @BindView(R.id.rl_news)
    RecyclerView mRlnews;
    @BindView(R.id.floatbutton)
    FloatingActionButton mFloatbutton;

    private ArrayList<DialyNewsBean.StoriesBean> mItemList;
    private ArrayList<DialyNewsBean.TopStoriesBean> mBannerList;
    private String mTime;
    private RlZhNewsAdapter mRlZhNewsAdapter;
    private String mDate;
    private List<DialyNewsBean.StoriesBean> mStories;
    private List<DialyNewsBean.TopStoriesBean> mTop_stories;
    private String mMs;

    public Zhihu_news_Fragment() {
        // Required empty public constructor
    }


    @Override
    protected ZhihuNewsP initPersenter() {
        return new ZhihuNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_news_;
    }

    @Override
    protected void initView() {

        mRlnews.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        mPersenter.getNewsData();
        mItemList = new ArrayList<>();
        mBannerList = new ArrayList<>();
        ;
        mTime = "今日新闻";
        mRlZhNewsAdapter = new RlZhNewsAdapter(mBannerList, mItemList, mTime, getActivity());
        mRlnews.setAdapter(mRlZhNewsAdapter);
        mRlnews.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMs != null) {
            mTime = mMs;
            Log.i(TAG, "onResume: 日历1111111111" + mTime);
            mRlZhNewsAdapter.setTime(mTime);
            mRlZhNewsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initListener() {
        mFloatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "柴慧茹是猪？！！！！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                startActivityForResult(intent,200);
            }
        });
    }


    @Override
    public void onNewsSuccess(DialyNewsBean pDialyNewsBean) {
        Log.d(TAG, "onNewsSuccess: " + pDialyNewsBean.toString());
        mStories = pDialyNewsBean.getStories();
        mTop_stories = pDialyNewsBean.getTop_stories();
        mItemList.clear();
        if (mStories != null && mStories.size() > 0) {
            mItemList.addAll(mStories);
            mRlZhNewsAdapter.setNewsList(mItemList);
        }
        mBannerList.clear();
        if (mTop_stories != null && mTop_stories.size() > 0) {
            mBannerList.addAll(mTop_stories);
            mRlZhNewsAdapter.setBannerList(mBannerList);
        }
        mRlZhNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String string) {
        Log.i(TAG, "onFail: " + string);
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200 && resultCode==300){
            mMs = data.getStringExtra("ms");
            if(mMs !=null){
                mPersenter.getBewforeData(mMs);
            }
        }
    }
}

