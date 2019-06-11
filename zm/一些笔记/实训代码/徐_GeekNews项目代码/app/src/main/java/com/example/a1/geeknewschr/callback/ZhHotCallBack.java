package com.example.a1.geeknewschr.callback;


import com.example.a1.geeknewschr.bean.ZhihuhotBean;

/**
 * Created by 1 on 2019/4/17.
 */

public interface ZhHotCallBack {
    void onHotSuccess(ZhihuhotBean pZhihuhotBean);
    void onHotFail(String string);
}
