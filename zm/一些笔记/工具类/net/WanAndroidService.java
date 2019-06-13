package com.jiyun.geek.net;

import com.jiyun.geek.bean.V2exNaviBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by $lzj on 2019/5/20.
 */
public interface WanAndroidService {
    String mBaseUrl = "https://www.wanandroid.com/";

    //节点列表
    @GET("navi/json")
    Observable<V2exNaviBean> getNodeNavi();
}
