package com.example.a1.geeknewschr.persenter.gank;


import com.example.a1.geeknewschr.base.BasePersenter;
import com.example.a1.geeknewschr.bean.gank.FuliBean;
import com.example.a1.geeknewschr.callback.gank.FuliCallBack;
import com.example.a1.geeknewschr.model.gank.FuliModel;
import com.example.a1.geeknewschr.view.gank.FuliView;

/**
 * Created by 1 on 2019/4/23.
 */

public class FuliP extends BasePersenter<FuliView> {

    private FuliModel mFuliModel;

    @Override
    protected void initModel() {
        mFuliModel = new FuliModel();
        models.add(mFuliModel);
    }
    public void getFuliData(int page){
            mFuliModel.getFulData(page, new FuliCallBack() {
                @Override
                public void onFuliSuccess(FuliBean pFuliBean) {
                    if (mView!=null){
                        mView.onFuliSuccess(pFuliBean);
                    }
                }

                @Override
                public void onFuliFail(String string) {
                    if (mView!=null){
                        mView.onFuliFail(string);
                    }
                }
            });
    }
}
