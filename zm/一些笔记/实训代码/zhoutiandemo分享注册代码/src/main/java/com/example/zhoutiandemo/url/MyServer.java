package com.example.zhoutiandemo.url;

import com.example.zhoutiandemo.bean.FuLiBean;
import com.example.zhoutiandemo.bean.LoginBean;
import com.example.zhoutiandemo.bean.YanZhengBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 王董辉    class
 */
public interface MyServer {
    //https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1

    public String url = "https://gank.io/api/data/";

    @GET("%E7%A6%8F%E5%88%A9/10/{page}")
    Call<FuLiBean> getData(@Path("page") int page);


    //登录
    public String dengluurl = "http://yun918.cn/study/public/index.php/";

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("username") String username,
                                @Field("password") String password);

    //验证码
    //http://yun918.cn/study/public/index.php/verify
    public String yanzhengurl = "http://yun918.cn/study/public/index.php/";

    @GET("verify")
    Call<YanZhengBean> getYanZheng();

    //http://yun918.cn/study/public/index.php/register
    public String zhuCeurl = "http://yun918.cn/study/public/index.php/register";

}
