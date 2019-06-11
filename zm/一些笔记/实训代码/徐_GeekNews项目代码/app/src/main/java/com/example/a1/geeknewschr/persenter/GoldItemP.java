package com.example.a1.geeknewschr.persenter;

import com.example.a1.geeknewschr.base.BasePersenter;
import com.example.a1.geeknewschr.bean.Gold_item;
import com.example.a1.geeknewschr.callback.GoldItemCallBack;
import com.example.a1.geeknewschr.model.GoldItemModel;
import com.example.a1.geeknewschr.view.gold.GoldItemV;

/**
 * Created by 1 on 2019/5/3.
 */

public class GoldItemP extends BasePersenter<GoldItemV> implements GoldItemCallBack {

    private GoldItemModel mGoldItemModel;

    @Override
    protected void initModel() {
        mGoldItemModel = new GoldItemModel();
        models.add(mGoldItemModel);
    }

    public void getGold(String context){
        if (mGoldItemModel!=null)
         mGoldItemModel.goldItem(context,this);
    }

    @Override
    public void onGoldItemSuccess(Gold_item pGold_item) {
        if (mView!=null){
            mView.onGoldItemSuccess(pGold_item);
        }
    }

    @Override
    public void onGoldFail(String string) {
        if (mView!=null){
            mView.onGoldFail(string);
        }
    }
}
