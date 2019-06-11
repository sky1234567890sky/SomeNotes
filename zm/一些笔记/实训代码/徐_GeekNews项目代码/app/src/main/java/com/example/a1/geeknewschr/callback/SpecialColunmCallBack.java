package com.example.a1.geeknewschr.callback;

import com.example.a1.geeknewschr.bean.SpecialColunmBean;

/**
 * Created by 1 on 2019/4/17.
 */

public interface SpecialColunmCallBack {
    void onSpSuccess(SpecialColunmBean pSpecialColunmBean);
    void onSpFail(String string);
}
