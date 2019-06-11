package com.example.a1.geeknewschr.persenter.gank;

import com.example.a1.geeknewschr.base.BasePersenter;
import com.example.a1.geeknewschr.bean.gank.IsoBean;
import com.example.a1.geeknewschr.callback.gank.IsoCallBack;
import com.example.a1.geeknewschr.model.gank.GankIsoModel;
import com.example.a1.geeknewschr.view.gank.IsoV;

/**
 * Created by 1 on 2019/4/22.
 */

public class IsoP extends BasePersenter<IsoV> implements IsoCallBack {

    private GankIsoModel mGankIsoModel;

    @Override
    protected void initModel() {
        mGankIsoModel = new GankIsoModel();
        models.add(mGankIsoModel);
    }
    public void getisoData(String teach, int num, int page){
        mGankIsoModel.getGankisodata(teach, num, page,this);
    }


    @Override
    public void onIsoSuccess(IsoBean pIsoBean) {
        mView.onIsoSuccess(pIsoBean);
    }

    @Override
    public void onIsoFail(String string) {
        mView.onIsoFail(string);
    }
}
