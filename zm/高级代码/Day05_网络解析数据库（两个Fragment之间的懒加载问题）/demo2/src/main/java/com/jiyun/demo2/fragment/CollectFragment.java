package com.jiyun.demo2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.demo2.R;
import com.jiyun.demo2.adapter.MyCollectAdapter;
import com.jiyun.demo2.bean.DbBean;
import com.jiyun.demo2.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment implements MyCollectAdapter.OnClickListener {


    private RecyclerView lv;
    private ArrayList<DbBean> list = new ArrayList<>();
    private MyCollectAdapter adapter;

    public CollectFragment() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()){
            initData();
        }else{
            list.clear();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_collect, container, false);
        initView(inflate);
//        initData();
        return inflate;
    }


    private void initData() {
        List<DbBean> query = DbUtils.getDbUtils().query();
        list.addAll(query);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    private void initView(View inflate) {
        lv = (RecyclerView) inflate.findViewById(R.id.lv);

        adapter = new MyCollectAdapter(getActivity(),list);
        lv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(layoutManager);

        adapter.setOnClickListener(this);
    }

    @Override
    public void onItemClick(int position, DbBean dbBean) {
        DbUtils.getDbUtils().delete(dbBean);

        list.remove(position);
        adapter.notifyDataSetChanged();
    }
}
