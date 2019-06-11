package com.example.a1.geeknewschr.base;

/**
 * Created by 1 on 2019/5/5.
 */

public interface BaseCallBack<T> {
    void onSuccess(T t);
    void onFail(String string);
}
