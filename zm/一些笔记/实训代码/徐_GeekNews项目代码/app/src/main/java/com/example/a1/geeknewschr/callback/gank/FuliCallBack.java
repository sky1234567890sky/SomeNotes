package com.example.a1.geeknewschr.callback.gank;

import com.example.a1.geeknewschr.bean.gank.FuliBean;

/**
 * Created by 1 on 2019/4/23.
 */

public interface FuliCallBack {
    void onFuliSuccess(FuliBean pFuliBean);

    void onFuliFail(String string);
}
