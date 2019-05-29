package com.example.along.day16_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by long on 2019/2/22.
 */

public class Fragment_A extends Fragment{

    private ArrayList<Info> list_a;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_a,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        RecyclerView rl_a = getView().findViewById(R.id.rl_a);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rl_a.setLayoutManager(linearLayoutManager);
        //数据源
        list_a = new ArrayList<>();
        for (int i=0;i<20;i++){
            list_a.add(new Info("fragment_a_"+i,""+i));
        }
        //创建适配器
        myAdapter = new MyAdapter(list_a,getActivity() );
        //绑定适配器
        rl_a.setAdapter(myAdapter);

        myAdapter.setMyOnclik(new MyAdapter.MyOnclik() {
            @Override
            public void myOnClikPosition(int position) {
                // 记录要 移除的数据
                Info info = list_a.get(position);
                //点那个  list 就  移除那个
                list_a.remove(position);
                myAdapter.notifyDataSetChanged();
                // 把记录的 传给 b
                    // 1 获得 actvity 对象
                MainActivity m = (MainActivity) getActivity();
                // 2 获得  要传递的 fragment_b 对象
                Fragment_B fragment_b = m.getFragment_b();
                // 3   fragment_b 对象  调用 fragment_b 的方法
                fragment_b.setInfo(info);
            }
        });
    }

    public void setInfo(Info info ){
        // 传递过来的数据添加 给集合
        list_a.add(info);
        // 刷新
        myAdapter.notifyDataSetChanged();
    }
}
