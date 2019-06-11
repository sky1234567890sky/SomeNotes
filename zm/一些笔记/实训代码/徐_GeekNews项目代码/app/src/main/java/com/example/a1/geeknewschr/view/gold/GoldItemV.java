package com.example.a1.geeknewschr.view.gold;

import com.example.a1.geeknewschr.base.BaseView;
import com.example.a1.geeknewschr.bean.Gold_item;
import com.example.a1.geeknewschr.callback.GoldItemCallBack;

/**
 * Created by 1 on 2019/5/3.
 */

public interface GoldItemV extends BaseView {
    void onGoldItemSuccess(Gold_item pGoldItemCallBack);
    void onGoldFail(String  string);
}
