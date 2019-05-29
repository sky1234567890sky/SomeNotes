package com.example.myfilemvpdomo.fram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfilemvpdomo.api.MyServer;
import com.example.myfilemvpdomo.R;
import com.example.myfilemvpdomo.adapter.MyHomeAdapter;
import com.example.myfilemvpdomo.beans.ArtDatas;
import com.example.myfilemvpdomo.beans.BeannerDatas;
import com.example.myfilemvpdomo.modle.MyModlelmpl;
import com.example.myfilemvpdomo.presenter.MyPreSenterlmpl;
import com.example.myfilemvpdomo.view.MyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements MyView {
    private XRecyclerView xrv;
    private List<ArtDatas.DataBean.DatasBean> artDatas;
    private List<BeannerDatas.DataBean> b;
    private MyHomeAdapter adapter;
    private int page = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        getData();
        getBeanners();
        return inflate;
    }
    private void getData() {
        MyPreSenterlmpl myPreSenterlmpl = new MyPreSenterlmpl(new MyModlelmpl(), this);
        myPreSenterlmpl.getArtDatas(page);
//        myPreSenterlmpl.getBanners();
    }
    private void getBeanners() {

        Retrofit rf = new Retrofit.Builder()
                .baseUrl(MyServer.path)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        rf.create(MyServer.class).BeannerDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeannerDatas>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BeannerDatas beannerDatas) {
                        b.addAll(beannerDatas.getData());
                        adapter.notifyDataSetChanged();
                        xrv.loadMoreComplete();
                        xrv.refreshComplete();
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView(View inflate) {
        xrv = (XRecyclerView) inflate.findViewById(R.id.xrv);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));

        artDatas = new ArrayList<>();
        b = new ArrayList<>();
        adapter = new MyHomeAdapter(getContext(), artDatas, b);
        xrv.setAdapter(adapter);

        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                artDatas.clear();
                b.clear();
                getData();
                getBeanners();
            }

            @Override
            public void onLoadMore() {
                ++page;
                getData();
            }
        });
    }
    @Override
    public void onSuccessArt(List<ArtDatas.DataBean.DatasBean> getArtDtas) {
        artDatas.addAll(getArtDtas);
        Log.i("tag", "数据1: "+getArtDtas.toString()+"\r\n"+"banner");
        adapter.notifyDataSetChanged();
        xrv.loadMoreComplete();
        xrv.refreshComplete();
    }
    @Override
    public void onSuccessBanner(List<BeannerDatas.DataBean> getBeanners) {
    }
    @Override
    public void onFail(String err) {
        Toast.makeText(getActivity(), ""+err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailBeannerS(String msg) {
        Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
    }
}
