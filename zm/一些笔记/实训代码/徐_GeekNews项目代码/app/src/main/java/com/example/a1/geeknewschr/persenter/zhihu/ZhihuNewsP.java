package com.example.a1.geeknewschr.persenter.zhihu;

import android.util.Log;

import com.example.a1.geeknewschr.base.BasePersenter;
import com.example.a1.geeknewschr.bean.DialyNewsBean;
import com.example.a1.geeknewschr.callback.DialyNewsCallBack;
import com.example.a1.geeknewschr.model.DialyNewsModel;
import com.example.a1.geeknewschr.view.zhihu.ZhihuNewsV;

/**
 * Created by 1 on 2019/5/3.
 */

public class ZhihuNewsP extends BasePersenter<ZhihuNewsV> implements DialyNewsCallBack {
    private DialyNewsModel mDialyNewsModel;

    @Override
    protected void initModel() {
        mDialyNewsModel = new DialyNewsModel();
        models.add(mDialyNewsModel);
    }
    public void getNewsData(){
        if (mDialyNewsModel!=null){
            Log.i("tag1", "getNewsData: "+"mcksmc");
            mDialyNewsModel.getData(this);
        }
    }

    @Override
    public void onNewsSuccess(DialyNewsBean pDialyNewsBean) {
        if (mView!=null){
            if(pDialyNewsBean != null){
                mView.onNewsSuccess(pDialyNewsBean);
            }
        }
    }

    @Override
    public void onFail(String string) {
        if (mView!=null){
            mView.onFail(string);
        }
    }

    public void getBewforeData(String date){
        if (mDialyNewsModel!=null){
            mDialyNewsModel.getBeforeData(date,this);
        }
    }
}
