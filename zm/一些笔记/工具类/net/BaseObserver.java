package com.jiyun.geek.net;

import android.nfc.Tag;

import com.jiyun.geek.utils.Logger;

import io.reactivex.Observer;

/**
 * Created by $lzj on 2019/5/6.
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private final String Tag = getClass().getName();

    @Override
    public void onComplete() {
        Logger.logD(Tag,"onComplete:");
    }

    @Override
    public void onError(Throwable e) {
        Logger.logD(Tag,"onError:" +e.getMessage());
    }
}
