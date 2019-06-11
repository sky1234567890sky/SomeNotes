package com.example.a1.geeknewschr.model.gank;


import com.example.a1.geeknewschr.api.MyServer_gank;
import com.example.a1.geeknewschr.base.BaseModel;
import com.example.a1.geeknewschr.bean.gank.FuliBean;
import com.example.a1.geeknewschr.callback.gank.FuliCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 1 on 2019/4/23.
 */

public class FuliModel extends BaseModel {
    public void getFulData(int page, final FuliCallBack pFuliCallBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer_gank.url_fl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MyServer_gank myServer_gank = retrofit.create(MyServer_gank.class);

        Observable<FuliBean> fuliData = myServer_gank.fuliData(page);
        fuliData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuliBean pFuliBean) {
                        pFuliCallBack.onFuliSuccess(pFuliBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pFuliCallBack.onFuliFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
