package com.example.a1.geeknewschr.view.gank;


import com.example.a1.geeknewschr.base.BaseView;
import com.example.a1.geeknewschr.bean.gank.IsoBean;

/**
 * Created by 1 on 2019/4/22.
 */

public interface IsoV extends BaseView {
    void onIsoSuccess(IsoBean pIsoBean);
    void onIsoFail(String string);
}
