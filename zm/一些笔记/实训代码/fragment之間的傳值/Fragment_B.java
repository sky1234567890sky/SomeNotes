package com.example.along.day16_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by long on 2019/2/22.
 */

public class Fragment_B extends Fragment{

    private ArrayList<Info> list_b;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_b,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        RecyclerView rl_b = getView().findViewById(R.id.rl_b);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rl_b.setLayoutManager(linearLayoutManager);
        ///数据源
        list_b = new ArrayList<>();
        for (int i=0;i<20;i++){
            list_b.add(new Info("fragment_b_"+i,""+i));
        }
        //创建适配器
        myAdapter = new MyAdapter(list_b,getActivity() );
        //绑定适配器
        rl_b.setAdapter(myAdapter);

        // 点击事件
        myAdapter.setMyOnclik(new MyAdapter.MyOnclik() {
            @Override
            public void myOnClikPosition(int position) {
                // 记录要 移除的数据
                Info info = list_b.get(position);
                //点那个  list 就  移除那个
                list_b.remove(position);
                myAdapter.notifyDataSetChanged();
                // 把记录的 传给 a
                // 1 获得 actvity 对象
                MainActivity m = (MainActivity) getActivity();
                // 2 获得  要传递的 fragment_b 对象
                Fragment_A fragment_a = m.getFragment_a();
                // 3   fragment_b 对象  调用 fragment_b 的方法
                fragment_a.setInfo(info);
            }
        });
    }
    public void setInfo(Info info ){
        // 传递过来的数据添加 给集合
        list_b.add(info);
        // 刷新
        myAdapter.notifyDataSetChanged();
    }
}
