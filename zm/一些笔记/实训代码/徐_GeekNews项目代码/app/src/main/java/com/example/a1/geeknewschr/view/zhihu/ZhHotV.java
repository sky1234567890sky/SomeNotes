package com.example.a1.geeknewschr.view.zhihu;


import com.example.a1.geeknewschr.base.BaseView;
import com.example.a1.geeknewschr.bean.ZhihuhotBean;

/**
 * Created by 1 on 2019/4/17.
 */

public interface ZhHotV extends BaseView {
    void onHotSuccess(ZhihuhotBean pZhihuhotBean);
    void onHotFail(String string);
}
