package com.example.a1.geeknewschr.model;


import com.example.a1.geeknewschr.api.MyServer;
import com.example.a1.geeknewschr.base.BaseModel;
import com.example.a1.geeknewschr.bean.SpecialColunmBean;
import com.example.a1.geeknewschr.callback.SpecialColunmCallBack;

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

public class SpecialColunmModel extends BaseModel {
    public void getSpColData(final SpecialColunmCallBack pSpecialColunmCallBack){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.url_zhihu)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<SpecialColunmBean> spacil = myServer.spacil();
        spacil.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpecialColunmBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    
                    }

                    @Override
                    public void onNext(SpecialColunmBean pSpecialColunmBean) {
                        pSpecialColunmCallBack.onSpSuccess(pSpecialColunmBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pSpecialColunmCallBack.onSpFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
