package com.jiyun.geek.net;


import com.jiyun.geek.bean.DailyNewsBean;
import com.jiyun.geek.bean.DailyNewsDetailsBean;
import com.jiyun.geek.bean.HotBean;
import com.jiyun.geek.bean.SectionsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhihuService {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    /**
     * 获取最新日报
     * @return
     */
    @GET("news/latest")
    Observable<DailyNewsBean> getLatestNews();

    /**
     * 专栏
     * @return
     */
    @GET("sections")
    Observable<SectionsBean> getSectionsData();

    /**
     * 热门
     * @return
     */
    @GET("news/hot")
    Observable<HotBean> getHotData();

    //news/{news_id}参数news_id上一个接口中
    @GET("news/{news_id}")
    Observable<DailyNewsDetailsBean> getDailyNewsDetail(@Path("news_id") int newsId);
}
