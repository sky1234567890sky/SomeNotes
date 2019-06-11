package com.example.a1.geeknewschr.model.gank;


import com.example.a1.geeknewschr.api.MyServer_gank;
import com.example.a1.geeknewschr.base.BaseModel;
import com.example.a1.geeknewschr.bean.gank.IsoBean;
import com.example.a1.geeknewschr.callback.gank.IsoCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 1 on 2019/4/22.
 */

public class GankIsoModel extends BaseModel {
    public void getGankisodata(String teach, int num, int page, final IsoCallBack pIsoCallBack){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer_gank.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer_gank myServer_gank = retrofit.create(MyServer_gank.class);
        Observable<IsoBean> data = myServer_gank.isoData(teach, num, page);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IsoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IsoBean pIsoBean) {
                        pIsoCallBack.onIsoSuccess(pIsoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pIsoCallBack.onIsoFail(e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
