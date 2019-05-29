package com.example.myfilemvpdomo.presenter;

import com.example.myfilemvpdomo.beans.ArtDatas;
import com.example.myfilemvpdomo.beans.BeannerDatas;
import com.example.myfilemvpdomo.callback.MyCallBack;
import com.example.myfilemvpdomo.modle.MyModel;
import com.example.myfilemvpdomo.view.MyView;

import java.util.List;

/**
 * Created by 苏克阳 on 2019/4/23.
 */

public class MyPreSenterlmpl implements MyPresenter, MyCallBack {
    private MyModel myModel;
    private MyView myView;

    public MyPreSenterlmpl(MyModel myModel, MyView myView) {
        this.myModel = myModel;
        this.myView = myView;
    }

    @Override
    public void getArtDatas(int page) {
        if (myModel!=null){
            myModel.getArtDatas(page,this);
        }
    }

    @Override
    public void getBanners() {
        if (myModel!=null){
            myModel.getBanners(this);
        }
    }
    @Override
    public void onSuccessArt(List<ArtDatas.DataBean.DatasBean> getArtDtas) {
        if (myView!=null){
            myView.onSuccessArt(getArtDtas);
        }
    }

    @Override
    public void onSuccessBanner(List<BeannerDatas.DataBean> getBeanners) {

        if (myView!=null){
            //onSuccessBanner(getBeanners);
        }

    }

    @Override
    public void onFail(String err) {
        if (myView!=null){
                myView.onFail(err);
        }
    }
    @Override
    public void onFailBeannerS(String msg) {
        if (myView!=null){
            myView.onFailBeannerS(msg);
        }
    }

}
