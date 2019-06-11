package com.example.a1.geeknewschr.view.zhihu;

import com.example.a1.geeknewschr.base.BaseView;
import com.example.a1.geeknewschr.bean.DialyNewsBean;

/**
 * Created by 1 on 2019/5/3.
 */

public interface ZhihuNewsV extends BaseView{
    void onNewsSuccess(DialyNewsBean pDialyNewsBean);
    void onFail(String string);
}
