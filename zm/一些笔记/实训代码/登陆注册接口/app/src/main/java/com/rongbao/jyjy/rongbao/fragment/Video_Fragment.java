package com.rongbao.jyjy.rongbao.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.rongbao.jyjy.rongbao.R;
import com.rongbao.jyjy.rongbao.adapter.fragment.Fragment_Video_Adapter;
import com.rongbao.jyjy.rongbao.base.BaseFragment;
import com.rongbao.jyjy.rongbao.base.BasePresent;
import com.rongbao.jyjy.rongbao.base.BaseView;
import com.rongbao.jyjy.rongbao.bean.Video_Bean;
import com.rongbao.jyjy.rongbao.present.fragment_presnet.Video_Presnet;
import com.rongbao.jyjy.rongbao.util.ToastUtil;
import com.rongbao.jyjy.rongbao.view.fragment.Video_View;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/4/24 0024.
 */

public class Video_Fragment extends BaseFragment<BasePresent, BaseView> implements Video_View {
    @BindView(R.id.video_recy)
    RecyclerView mVideoRecy;
    @BindView(R.id.video_smart)
    SmartRefreshLayout mVideoSmart;
    private Fragment_Video_Adapter fragment_video_adapter;
    private String at;

    @Override
    protected BasePresent getPresnet() {
        return new Video_Presnet();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.video_fragment;
    }



    @Override
    protected void initView() {
        mVideoRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Video_Bean.DataBean.FeedsListBean> video_beans = new ArrayList<>();
        fragment_video_adapter = new Fragment_Video_Adapter(getContext(),video_beans);
        mVideoRecy.setAdapter(fragment_video_adapter);
    }
    @Override
    protected void initData() {
        basefragmentpresent.getData();
    }
    @Override
    protected void initClick() {
        mVideoSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                fragment_video_adapter.video_beans.clear();
                initData();
                refreshLayout.finishRefresh();
            }
        });

    }

    @Override
    public String getPage() {
        return at;
    }

    @Override
    public void Onsuccessful(Video_Bean bean) {
        if(bean!=null){
        fragment_video_adapter.getData(bean);


            Video_Bean.DataBean.FeedsListBean feedsListBean = bean.getData().getFeeds_list().get(bean.getData().getFeeds_list().size() - 1);
            String created_at = feedsListBean.getCreated_at();
            SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                long time = yyyyMMddHHmmss.parse(created_at).getTime();
                at = String.valueOf(time);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void OnFaild(String messge) {
        ToastUtil.ShowToast(messge);
    }
}
