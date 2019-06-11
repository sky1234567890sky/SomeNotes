package com.example.a1.geeknewschr.callback;


import com.example.a1.geeknewschr.bean.DialyNewsBean;

/**
 * Created by 1 on 2019/4/17.
 */

public interface DialyNewsCallBack {
    void onNewsSuccess(DialyNewsBean pDialyNewsBean);
    void onFail(String string);
}
