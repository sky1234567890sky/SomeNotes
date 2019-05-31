package com.jiyun.day03;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by $lzj on 2019/4/2.
 */
public interface MyServer {

    //https://api.apiopen.top/getJoke?page=1&count=2&type=video
    //1,主机路径
    public String Url = "https://api.apiopen.top/";

    @GET("getJoke?page=1&count=2&type=video")
    Call<ResponseBody> getData1();

    @GET("getJoke?")
    Call<ResponseBody> getData2(@Query("page") int page,@Query("count") int count,@Query("type") String type);

    @GET("getJoke?")
    Call<ResponseBody> getData3(@QueryMap Map<String,Object> map);


    @FormUrlEncoded
    @POST("getJoke?")
    Call<ResponseBody> postData1(@Field("page") int page, @Field("count") int count, @Field("type") String type);

    @POST("getJoke?")
    @FormUrlEncoded
    Call<ResponseBody> postData2(@FieldMap Map<String,Object> map);
}
