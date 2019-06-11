package com.example.a1.geeknewschr.view.zhihu;


import com.example.a1.geeknewschr.base.BaseView;
import com.example.a1.geeknewschr.bean.SpecialColunmBean;

/**
 * Created by 1 on 2019/4/17.
 */

public interface ZhSpColunV extends BaseView {
    void onSpSuccess(SpecialColunmBean pSpecialColunmBean);
    void onSpFail(String string);
}
