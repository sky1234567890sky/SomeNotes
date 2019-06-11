package com.example.a1.geeknewschr.fragment.zhihu;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.RlZhSpecialAdapter;
import com.example.a1.geeknewschr.base.BaseFragment;
import com.example.a1.geeknewschr.base.BasePersenter;
import com.example.a1.geeknewschr.bean.SpecialColunmBean;
import com.example.a1.geeknewschr.persenter.zhihu.ZhSpColunmP;
import com.example.a1.geeknewschr.view.zhihu.ZhSpColunV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Zhihu_special_column_Fragment extends BaseFragment<ZhSpColunV,ZhSpColunmP> implements ZhSpColunV {


    private static final String TAG = "zhsp";
    @BindView(R.id.rl_special)
    RecyclerView mRlSpecial;

    private ArrayList<SpecialColunmBean.DataBean> mSpecialList;
    private RlZhSpecialAdapter mRlZhSpecialAdapter;
    public Zhihu_special_column_Fragment() {
        // Required empty public constructor
    }

    @Override
    protected ZhSpColunmP initPersenter() {
        return new ZhSpColunmP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_special_column_;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mRlSpecial.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mSpecialList = new ArrayList<>();
        mPersenter.getSpData();
        mRlZhSpecialAdapter = new RlZhSpecialAdapter(mSpecialList, getActivity());
        mRlSpecial.setAdapter(mRlZhSpecialAdapter);
    }

    @Override
    public void onSpSuccess(SpecialColunmBean pSpecialColunmBean) {
        List<SpecialColunmBean.DataBean> stories = pSpecialColunmBean.getData();
        mSpecialList.addAll(stories);
        Log.i(TAG, "onSpSuccess: "+mSpecialList.toString());
        mRlZhSpecialAdapter.setSpecialList(mSpecialList);
        mRlZhSpecialAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSpFail(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onSpFail: "+string);
    }

}
