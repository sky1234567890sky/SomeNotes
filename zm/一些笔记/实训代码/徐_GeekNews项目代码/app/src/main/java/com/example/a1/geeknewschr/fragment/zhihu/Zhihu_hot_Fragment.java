package com.example.a1.geeknewschr.fragment.zhihu;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.RlZhHotAdapter;
import com.example.a1.geeknewschr.base.BaseFragment;
import com.example.a1.geeknewschr.bean.ZhihuhotBean;
import com.example.a1.geeknewschr.persenter.zhihu.ZhHotP;
import com.example.a1.geeknewschr.view.zhihu.ZhHotV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Zhihu_hot_Fragment extends BaseFragment<ZhHotV,ZhHotP> implements ZhHotV {


    private static final String TAG = "zhhot";
    @BindView(R.id.rl_hot)
    RecyclerView mRlHot;

    private ArrayList<ZhihuhotBean.RecentBean> mZhHotList;
    private RlZhHotAdapter mRlZhHotAdapter;
    public Zhihu_hot_Fragment() {
        // Required empty public constructor
    }

    @Override
    protected ZhHotP initPersenter() {
        return new ZhHotP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_hot_;
    }

    @Override
    protected void initView() {
        mRlHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        mZhHotList = new ArrayList<>();
        mRlZhHotAdapter = new RlZhHotAdapter(mZhHotList, getActivity());
        mRlHot.setAdapter(mRlZhHotAdapter);
    }

    @Override
    protected void initData() {
        mPersenter.getHotData();
    }



    @Override
    public void onHotSuccess(ZhihuhotBean pZhihuhotBean) {
        List<ZhihuhotBean.RecentBean> recent = pZhihuhotBean.getRecent();
        Log.i(TAG, "onHotSuccess: "+recent.toString());
        mZhHotList.addAll(recent);
        mRlZhHotAdapter.setZhHotList(mZhHotList);
        mRlZhHotAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHotFail(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}
