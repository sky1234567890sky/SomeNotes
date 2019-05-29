package com.example.myfilemvpdomo.modle;

import com.example.myfilemvpdomo.callback.MyCallBack;

/**
 * Created by 苏克阳 on 2019/4/23.
 */

public interface MyModel {
    void getArtDatas(int page, MyCallBack myCallBack);

    void getBanners(MyCallBack myCallBack);

}
