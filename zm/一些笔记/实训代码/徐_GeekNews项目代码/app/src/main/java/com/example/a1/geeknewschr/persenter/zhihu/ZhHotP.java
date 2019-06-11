package com.example.a1.geeknewschr.persenter.zhihu;


import com.example.a1.geeknewschr.base.BasePersenter;
import com.example.a1.geeknewschr.bean.ZhihuhotBean;
import com.example.a1.geeknewschr.callback.ZhHotCallBack;
import com.example.a1.geeknewschr.model.ZhHotModel;
import com.example.a1.geeknewschr.view.zhihu.ZhHotV;

/**
 * Created by 1 on 2019/4/17.
 */
//柴慧茹  1808b
public class ZhHotP extends BasePersenter<ZhHotV> implements ZhHotCallBack {

    private ZhHotModel mZhHotModel;

    @Override
    protected void initModel() {
        mZhHotModel = new ZhHotModel();
        models.add(mZhHotModel);
    }
    public void getHotData(){
        if (mZhHotModel!=null) {
            mZhHotModel.getHot(this);
        }
    }

    @Override
    public void onHotSuccess(ZhihuhotBean pZhihuhotBean) {
        mView.onHotSuccess(pZhihuhotBean);
    }

    @Override
    public void onHotFail(String string) {
        mView.onHotFail(string);
    }
}
