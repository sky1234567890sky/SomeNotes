package com.example.a1.geeknewschr.persenter.zhihu;


import com.example.a1.geeknewschr.base.BasePersenter;
import com.example.a1.geeknewschr.bean.SpecialColunmBean;
import com.example.a1.geeknewschr.callback.SpecialColunmCallBack;
import com.example.a1.geeknewschr.model.SpecialColunmModel;
import com.example.a1.geeknewschr.view.zhihu.ZhSpColunV;

/**
 * Created by 1 on 2019/4/17.
 */

public class ZhSpColunmP extends BasePersenter<ZhSpColunV> {

    private SpecialColunmModel mSpecialColunmModel;

    @Override
    protected void initModel() {
        mSpecialColunmModel = new SpecialColunmModel();
        models.add(mSpecialColunmModel);
    }

    public void getSpData() {
        if (mSpecialColunmModel != null) {
            mSpecialColunmModel.getSpColData(new SpecialColunmCallBack() {
                @Override
                public void onSpSuccess(SpecialColunmBean pSpecialColunmBean) {
                    mView.onSpSuccess(pSpecialColunmBean);
                }

                @Override
                public void onSpFail(String string) {

                    mView.onSpFail(string);


                }
            });
        }
    }

}
