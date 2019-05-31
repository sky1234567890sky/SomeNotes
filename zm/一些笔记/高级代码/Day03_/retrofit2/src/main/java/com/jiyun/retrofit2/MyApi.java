package com.jiyun.retrofit2;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by $lzj on 2019/4/3.
 */
public interface MyApi {

    //https://api.apiopen.top/getJoke?page=1&count=2&type=video
    //https://api.apiopen.top/recommendPoetry
    public String URL = "https://api.apiopen.top/";

    //From   +    Headers、Header请求头   +    Body请求体
    @POST("getJoke?")
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    Call<ResponseBody> formData1(@Body RequestBody body);

    //JSON
    @POST("getJoke?")
    Call<ResponseBody> jsonData2(@Body RequestBody body, @Header("Content-Type") String type);

    //NO
    @POST("recommendPoetry")
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    Call<ResponseBody> noData(@Body RequestBody body);

    //通用
    @POST
    Call<ResponseBody> data(@Url String url,@Body RequestBody body,@Header("Content-Type") String head);
}
