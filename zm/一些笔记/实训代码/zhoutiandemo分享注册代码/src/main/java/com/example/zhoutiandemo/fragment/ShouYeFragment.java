package com.example.zhoutiandemo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.zhoutiandemo.R;
import com.example.zhoutiandemo.adapter.ShouYeAdapter;
import com.example.zhoutiandemo.bean.FuLiBean;
import com.example.zhoutiandemo.dao.ShuJuKuDemoDao;
import com.example.zhoutiandemo.db.BaseApp;
import com.example.zhoutiandemo.db.ShuJuKuDemo;
import com.example.zhoutiandemo.url.MyServer;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouYeFragment extends Fragment {


    private XRecyclerView rlv;
    private int page = 1;
    private LinearLayoutManager manager;
    private ShouYeAdapter adapter;
    private ArrayList<FuLiBean.ResultsBean> list;

    public ShouYeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_shou_ye, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MyServer.url)
                .build();

        MyServer myServer = retrofit.create(MyServer.class);

        Call<FuLiBean> call = myServer.getData(page);

        call.enqueue(new Callback<FuLiBean>() {
            @Override
            public void onResponse(Call<FuLiBean> call, Response<FuLiBean> response) {
                ArrayList<FuLiBean.ResultsBean> results = (ArrayList<FuLiBean.ResultsBean>) response.body().getResults();
                list.addAll(results);
                adapter.setList(list);
                adapter.notifyDataSetChanged();

                rlv.loadMoreComplete();
                rlv.refreshComplete();
            }

            @Override
            public void onFailure(Call<FuLiBean> call, Throwable t) {
                Toast.makeText(getContext(), "请求数据错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initPoPupWindow(final int position) {
        View inflate = View.inflate(getActivity(), R.layout.popupwindow_shoucang, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.E3C6C6));

        popupWindow.setContentView(inflate);
        popupWindow.setTouchable(true);

        popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);

        Button shoucang = inflate.findViewById(R.id.popup_shoucang);

        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //数据库操作
                initinsert(position, popupWindow);
            }
        });
    }

    private void initinsert(int position, PopupWindow popupWindow) {

        String url = list.get(position).getUrl();
        ShuJuKuDemo shuju = getShuju(url);

        if (shuju == null) {
            ShuJuKuDemo shuJuKuDemo = new ShuJuKuDemo();
            shuJuKuDemo.setUrl(list.get(position).getUrl());

            BaseApp.getBaseApp().getShuJuKuDemoDao().insert(shuJuKuDemo);
            Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        } else {
            Toast.makeText(getContext(), "已经收藏过了，亲~~", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        }


    }

    public ShuJuKuDemo getShuju(String url) {

        return BaseApp.getBaseApp().getShuJuKuDemoDao().queryBuilder()
                .where(ShuJuKuDemoDao.Properties.Url.eq(url))
                .build()
                .unique();
    }

    private void initView(View inflate) {
        rlv = (XRecyclerView) inflate.findViewById(R.id.rlv);

        list = new ArrayList<>();
        adapter = new ShouYeAdapter(list, getActivity());
        rlv.setAdapter(adapter);
        manager = new LinearLayoutManager(getContext());
        rlv.setLayoutManager(manager);

        adapter.setOnItemLongClickListenr(new ShouYeAdapter.OnItemLongClickListenr() {
            @Override
            public void onitemlongclick(int position) {

                initPoPupWindow(position);

            }
        });

        rlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                list.clear();
                initData();
            }

            @Override
            public void onLoadMore() {
                page++;
                initData();
            }
        });
    }
}
