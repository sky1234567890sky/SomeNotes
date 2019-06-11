package com.rongbao.jyjy.rongbao.util;

import com.rongbao.jyjy.rongbao.apiservice.Apiservice;
import com.rongbao.jyjy.rongbao.callresult.Callresult;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019/4/21 0021.
 */

public class ModelUtils {

    public static ModelUtils sModel;

    public ModelUtils() {
        sModel = this;
    }

    public static ModelUtils getInstance() {
        if (sModel == null) {
            synchronized (ModelUtils.class) {
                if (sModel == null) {
                    sModel = new ModelUtils();
                }
            }
        }
        return sModel;
    }

    public static Apiservice getNetMessage(String baseurl) {
        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Apiservice.class);
    }

    public <A> void getNetWork(Observable<A> observable, final Callresult<A> callresult ) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<A>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(A a) {
                        callresult.Onsuccessful(a);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }




}

