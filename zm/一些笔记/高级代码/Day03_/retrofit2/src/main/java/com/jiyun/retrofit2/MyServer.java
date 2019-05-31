package com.jiyun.retrofit2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by $lzj on 2019/4/3.
 */
public interface MyServer {

    // http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1
    public String Url1 = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    // http://api.shujuzhihui.cn/api/news/categories?appKey=908ca46881994ffaa6ca20b31755b675
    String Url2 = "http://api.shujuzhihui.cn/api/news/";//必须以反斜杠结尾

    //Path路径替换
    @GET("20/{page}")
    Call<ResponseBody> pathData(@Path("page") int page);


    //Url路径拼接
    @GET
    Call<ResponseBody> urlData1(@Url String url);

    //Url+Query  路径拼接+参数拼接
    @GET
    Call<ResponseBody> urlData2(@Url String url, @Query("appKey") String appkey);

    @POST
    Call<ResponseBody> urlData3(@Url String url, @Field("appKey") String appkey);
}
