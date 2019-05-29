package com.example.myfilemvpdomo.callback;

import com.example.myfilemvpdomo.beans.ArtDatas;
import com.example.myfilemvpdomo.beans.BeannerDatas;

import java.util.List;

/**
 * Created by 苏克阳 on 2019/4/23.
 */

public interface MyCallBack {

    void onSuccessArt(List<ArtDatas.DataBean.DatasBean> getArtDtas);
    void onFail(String err);

    void onSuccessBanner(List<BeannerDatas.DataBean> getBeanners);
    void onFailBeannerS(String msg);

}
