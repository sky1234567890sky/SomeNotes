package com.example.a1.geeknewschr.model;

import android.util.Log;


import com.example.a1.geeknewschr.api.MyServer;
import com.example.a1.geeknewschr.base.BaseModel;
import com.example.a1.geeknewschr.bean.WeChaBean;
import com.example.a1.geeknewschr.callback.WeChatCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 1 on 2019/4/11.
 */

public class WeChatMoel extends BaseModel {
    private static final String TAG = "WeChatMoel";

    public void getWeChat(String key, int num, int page, final WeChatCallBack pWeChatCallBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.url_wechat)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<WeChaBean> wechat = myServer.wechat(key, num, page);
        wechat.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<WeChaBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
//                            Log.d(TAG, "onSubscribe: ");
//                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(WeChaBean pWeChaBean) {
                            pWeChatCallBack.onWeChatSuccess(pWeChaBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            pWeChatCallBack.onWechatFail(e.getMessage());
                        }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
