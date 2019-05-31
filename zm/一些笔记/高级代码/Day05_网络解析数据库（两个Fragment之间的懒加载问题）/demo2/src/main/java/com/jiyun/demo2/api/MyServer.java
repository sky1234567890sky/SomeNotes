package com.jiyun.demo2.api;

import com.jiyun.demo2.bean.ListBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by $lzj on 2019/4/8.
 */
public interface MyServer {

    //https://api.apiopen.top/getJoke?page=1&count=2&type=video
    public String URL = "https://api.apiopen.top/";

    @POST("getJoke?")
    @FormUrlEncoded
    Call<ListBean> getData(@Field("page") int page,@Field("count") int count,@Field("type") String type);
}
