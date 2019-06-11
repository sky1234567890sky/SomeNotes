package com.example.a1.geeknewschr.model;


import com.example.a1.geeknewschr.api.MyServer;
import com.example.a1.geeknewschr.base.BaseModel;
import com.example.a1.geeknewschr.bean.ZhihuhotBean;
import com.example.a1.geeknewschr.callback.ZhHotCallBack;

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

public class ZhHotModel extends BaseModel {

    public void getHot(final ZhHotCallBack pZhHotCallBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.url_zhihu)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<ZhihuhotBean> hot = myServer.hot();
        hot.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuhotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhihuhotBean pZhihuhotBean) {
                        pZhHotCallBack.onHotSuccess(pZhihuhotBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pZhHotCallBack.onHotFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
