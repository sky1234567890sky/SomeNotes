package com.example.myfilemvpdomo.modle;

import com.example.myfilemvpdomo.api.MyServer;
import com.example.myfilemvpdomo.beans.ArtDatas;
import com.example.myfilemvpdomo.beans.BeannerDatas;
import com.example.myfilemvpdomo.callback.MyCallBack;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 苏克阳 on 2019/4/23.
 */
public class MyModlelmpl implements MyModel {

    @Override
    public void getArtDatas(int page, final MyCallBack myCallBack) {
        Retrofit rf = new Retrofit.Builder()
                .baseUrl(MyServer.path)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        rf.create(MyServer.class).ArtData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtDatas>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ArtDatas artDatas) {
                        if (artDatas!=null &&artDatas.getData().getDatas().size()>0){
                            if (artDatas.getErrorCode() ==0){
                                myCallBack.onSuccessArt(artDatas.getData().getDatas());
                            }else{
                                myCallBack.onFail(artDatas.getErrorMsg());
                            }

                        }else{
                            myCallBack.onFail("错误");
                        }

                    }
                    @Override
                    public void onError(Throwable e) {
                        myCallBack.onFailBeannerS(e.getMessage());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    public void getBanners(final MyCallBack myCallBack) {

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
                        if (beannerDatas!=null && beannerDatas.getData().size()>0){
                            if (beannerDatas.getErrorCode() == 0){
                                myCallBack.onSuccessBanner(beannerDatas.getData());
                            }else{
                                myCallBack.onFail(beannerDatas.getErrorMsg());
                            }
                        }else{
                            myCallBack.onFail("错误");
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        myCallBack.onFailBeannerS(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
