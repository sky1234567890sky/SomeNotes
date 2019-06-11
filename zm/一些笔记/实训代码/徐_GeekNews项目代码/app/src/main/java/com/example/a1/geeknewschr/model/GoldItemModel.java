package com.example.a1.geeknewschr.model;
import com.example.a1.geeknewschr.api.MyServer_gank;
import com.example.a1.geeknewschr.base.BaseModel;
import com.example.a1.geeknewschr.bean.Gold_item;
import com.example.a1.geeknewschr.callback.GoldItemCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 1 on 2019/5/3.
 */

public class GoldItemModel extends BaseModel {
    public void goldItem (String context, final GoldItemCallBack pGoldItemCallBack){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer_gank.url_gold)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer_gank myServer_gank = retrofit.create(MyServer_gank.class);
        Observable<Gold_item> gold = myServer_gank.gold(context);
        gold.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Gold_item>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Gold_item pGold_item) {
                        pGoldItemCallBack.onGoldItemSuccess(pGold_item);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pGoldItemCallBack.onGoldFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
