package com.example.a1.geeknewschr.view.gank;


import com.example.a1.geeknewschr.base.BaseView;
import com.example.a1.geeknewschr.bean.gank.FuliBean;

/**
 * Created by 1 on 2019/4/23.
 */

public interface FuliView extends BaseView {
    void onFuliSuccess(FuliBean pFuliBean);

    void onFuliFail(String string);
}
