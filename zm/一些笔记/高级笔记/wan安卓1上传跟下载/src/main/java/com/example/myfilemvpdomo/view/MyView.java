package com.example.myfilemvpdomo.view;

import com.example.myfilemvpdomo.beans.ArtDatas;
import com.example.myfilemvpdomo.beans.BeannerDatas;

import java.util.List;

/**
 * Created by 苏克阳 on 2019/4/23.
 */

public interface MyView {

    void onSuccessArt(List<ArtDatas.DataBean.DatasBean> getArtDtas);
    void onSuccessBanner(List<BeannerDatas.DataBean> getBeanners);

    void onFail(String err);
    void onFailBeannerS(String msg);

}
