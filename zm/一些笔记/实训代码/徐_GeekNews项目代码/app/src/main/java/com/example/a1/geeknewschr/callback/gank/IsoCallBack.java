package com.example.a1.geeknewschr.callback.gank;


import com.example.a1.geeknewschr.bean.gank.IsoBean;

/**
 * Created by 1 on 2019/4/22.
 */

public interface IsoCallBack {
    void onIsoSuccess(IsoBean pIsoBean);
    void onIsoFail(String string);
}
