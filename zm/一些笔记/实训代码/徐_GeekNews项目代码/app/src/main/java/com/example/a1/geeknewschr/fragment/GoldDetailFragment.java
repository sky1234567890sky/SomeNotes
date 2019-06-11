package com.example.a1.geeknewschr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a1.geeknewschr.R;
import com.example.a1.geeknewschr.adapter.RlGoldItemAdapter;
import com.example.a1.geeknewschr.base.BaseFragment;
import com.example.a1.geeknewschr.base.Constants;
import com.example.a1.geeknewschr.bean.Gold_item;
import com.example.a1.geeknewschr.persenter.GoldItemP;
import com.example.a1.geeknewschr.view.gold.GoldItemV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldDetailFragment extends BaseFragment<GoldItemV, GoldItemP> implements GoldItemV {


    private static final String TAG = "golditem";
    @BindView(R.id.rl)
    RecyclerView mRl;
    Unbinder unbinder;
    private String mString;
    private ArrayList<Gold_item.ResultsBean> mGoldItemList;
    private RlGoldItemAdapter mRlGoldItemAdapter;

    public GoldDetailFragment() {
        // Required empty public constructor
    }

    public static GoldDetailFragment newInstance(String text) {
        GoldDetailFragment goldDetailFragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA, text);
        goldDetailFragment.setArguments(bundle);
        return goldDetailFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        mString = arguments.getString(Constants.DATA);
    }

    @Override
    protected GoldItemP initPersenter() {
        return new GoldItemP();
    }

    @Override
    protected void initData() {
        mPersenter.getGold(mString);

        mRl.setLayoutManager(new LinearLayoutManager(getActivity()));
        mGoldItemList = new ArrayList<>();
        mRlGoldItemAdapter = new RlGoldItemAdapter(mGoldItemList, getActivity());
        mRl.setAdapter(mRlGoldItemAdapter);

    }

    @Override
    public void onGoldItemSuccess(Gold_item pGoldItemCallBack) {
        List<Gold_item.ResultsBean> results = pGoldItemCallBack.getResults();
        mGoldItemList.addAll(results);
        mRlGoldItemAdapter.setGoldItemList(mGoldItemList);
        mRlGoldItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGoldFail(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onGoldFail: "+string);
    }


}
