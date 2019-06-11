package com.example.a1.geeknewschr.api;


import com.example.a1.geeknewschr.bean.Gold_item;
import com.example.a1.geeknewschr.bean.gank.FuliBean;
import com.example.a1.geeknewschr.bean.gank.IsoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 1 on 2019/4/22.
 */

public interface MyServer_gank {
    public String url = "http://gank.io/api/";

    @GET("data/{tech}/{num}/{page}")
    Observable<IsoBean> isoData(@Path("tech") String teach, @Path("num") int num, @Path("page") int page);

    public String url_fl = "http://gank.io/api/";

    @GET("data/%E7%A6%8F%E5%88%A9/10/{page}")
    Observable<FuliBean> fuliData(@Path("page") int page);

    public String url_suiji = "http://gank.io/api/random/data/Android/20";

//    @GET("Android/20")
//    Observable<> meinv();

     String url_gold = "http://gank.io/api/search/query/{搜索字段}/category/";

    @GET("{context}/count/10/page/1")
    Observable<Gold_item> gold(@Path("context") String context);

}
