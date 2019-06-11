package com.example.a1.geeknewschr.net;


/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String msg);
}
