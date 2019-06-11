package com.example.a1.geeknewschr.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.WechatAdapter;
import com.example.a1.geeknewschr.base.BaseFragment;
import com.example.a1.geeknewschr.bean.WeChaBean;
import com.example.a1.geeknewschr.persenter.WechatP;
import com.example.a1.geeknewschr.view.WechatV;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatFragment extends BaseFragment<WechatV, WechatP> implements WechatV {

    @BindView(R.id.rl_wechat)
    RecyclerView mRlWechat;
    private ArrayList<WeChaBean.NewslistBean> mWechatList;
    private WechatAdapter mWechatAdapter;
    int page = 1;
    private String key = "52b7ec3471ac3bec6846577e79f20e4c";
    private int num = 10;

    public WeChatFragment() {
        // Required empty public constructor
    }


    @Override
    protected WechatP initPersenter() {
        return new WechatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_we_chat;
    }

    @Override
    protected void initView() {
        mRlWechat.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWechatList = new ArrayList<>();
        mWechatAdapter = new WechatAdapter(mWechatList, getActivity());
        mRlWechat.setAdapter(mWechatAdapter);
//        mRlWechat.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                page = 1;
//                initData();
//            }
//
//            @Override
//            public void onLoadMore() {
//                ++page;
//                initData();
//
//            }
//        });
    }
    @Override
    protected void initData() {
        super.initData();
        mPersenter.getdata(key,num,page);
    }
    @Override
    public void onWeChatSuccess(WeChaBean bean) {
        List<WeChaBean.NewslistBean> newslist = bean.getNewslist();
        mWechatList.addAll(newslist);
        mWechatAdapter.setWechatList(mWechatList);
        mWechatAdapter.notifyDataSetChanged();
    }

    @Override
    public void onWechatFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
