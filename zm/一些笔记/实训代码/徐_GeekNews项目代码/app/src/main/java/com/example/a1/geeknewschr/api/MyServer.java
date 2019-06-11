package com.example.a1.geeknewschr.api;


import com.example.a1.geeknewschr.bean.DialyNewsBean;
import com.example.a1.geeknewschr.bean.NewMsgBean;
import com.example.a1.geeknewschr.bean.SpecialColunmBean;
import com.example.a1.geeknewschr.bean.WeChaBean;
import com.example.a1.geeknewschr.bean.ZhihuhotBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 1 on 2019/4/2.
 */

public interface MyServer {
    public String url = "http://yun918.cn/study/public/index.php/";

//    @FormUrlEncoded
//    @POST("login")
//    Observable<LoginBean> login(@Field("username") String name, @Field("password") String psw);


    public String url_wechat = "http://api.tianapi.com/";

    @GET("wxnew/")
    Observable<WeChaBean> wechat(@Query("key") String query, @Query("num") int num, @Query("page") int page);
//    http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1


//    http://news-at.zhihu.com/api/4/
    public String url_zhihu = "http://news-at.zhihu.com/api/4/";

    @GET("news/hot")
    Observable<ZhihuhotBean> hot();

    @GET("sections")
    Observable<SpecialColunmBean> spacil();

    @GET("news/latest")
    Observable<DialyNewsBean> dialyNews();

    @GET("news/before/{date}")
    Observable<DialyNewsBean> beforeData(@Path("date") String date);

    @GET("news/{id}")
    Observable<NewMsgBean> newsMsg(@Path("id") int id);


}

