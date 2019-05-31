package com.jiyun.retrofit3;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by $lzj on 2019/4/3.
 */
public interface MyData {

    public String Url1 = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    @GET("20/{page}")
    Call<ResponseBody> getData(@Path("page") int page);

    @GET("20/{page}")
    Call<Bean> getData2(@Path("page") int page);
}
