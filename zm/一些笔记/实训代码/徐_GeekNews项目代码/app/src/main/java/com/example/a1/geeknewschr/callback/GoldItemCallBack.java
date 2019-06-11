package com.example.a1.geeknewschr.callback;

import com.example.a1.geeknewschr.bean.Gold_item;

/**
 * Created by 1 on 2019/5/3.
 */

public interface GoldItemCallBack {
    void onGoldItemSuccess(Gold_item pGold_item);
    void onGoldFail(String  string);
}
