package com.rongbao.jyjy.rongbao.apiservice;

import com.rongbao.jyjy.rongbao.bean.Login_Bean;
import com.rongbao.jyjy.rongbao.bean.Register_Bean;
import com.rongbao.jyjy.rongbao.bean.Veridity_Bean;
import com.rongbao.jyjy.rongbao.bean.Video_Bean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2019/4/17 0017.
 */

public interface Apiservice {
    String  Zong="http://yun918.cn/study/public/index.php/";
    @GET("verify")
    Observable<Veridity_Bean> getVerifity();
    @POST("login")
    @FormUrlEncoded
    Observable<Login_Bean> getLogin(@Field("username") String username, @Field("password") String password);
    @POST("register")
    @FormUrlEncoded
    Observable<Register_Bean> getRegister(@Field("username") String username, @Field("password") String password,
                                          @Field("phone") String phone, @Field("verify")String verify);
    @POST("uploadheader")
    @Multipart
    Observable<ResponseBody> getHeard_port(RequestBody key , RequestBody  uid, MultipartBody  file );
    String VideoUrl="http://bkbapi.dqdgame.com/";
    @GET("group/app/thread/videoList")
    Observable<Video_Bean> getVideoPlayer(@Query("at") String at);

}
