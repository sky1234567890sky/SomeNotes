package com.example.a1.geeknewschr.model;

import android.util.Log;

import com.example.a1.geeknewschr.api.MyServer;
import com.example.a1.geeknewschr.base.BaseModel;
import com.example.a1.geeknewschr.bean.DialyNewsBean;
import com.example.a1.geeknewschr.callback.DialyNewsCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 1 on 2019/4/17.
 */

public class DialyNewsModel extends BaseModel {
    public void getData(final DialyNewsCallBack pDialyNewsCallBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.url_zhihu)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<DialyNewsBean> news = myServer.dialyNews();
        news.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DialyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DialyNewsBean pDialyNewsBean) {
                        Log.i("tag", "onNext: "+pDialyNewsBean.toString());
                        pDialyNewsCallBack.onNewsSuccess(pDialyNewsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialyNewsCallBack.onFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getBeforeData(String date, final DialyNewsCallBack pDialyNewsCallBack){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.url_zhihu)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<DialyNewsBean> beforeData = myServer.beforeData(date);
        beforeData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DialyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DialyNewsBean pBeforeNewsBean) {
                        pDialyNewsCallBack.onNewsSuccess(pBeforeNewsBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialyNewsCallBack.onFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
