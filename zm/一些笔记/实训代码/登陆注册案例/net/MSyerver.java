package com.example.login_registerdemo.net;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by 华为 on 2019/6/10.
 */
public interface MSyerver {
    String login="http://yun918.cn/study/public/index.php/";
//    username:“zhangsan”;  //注册的用户名或者注册的手机号，二者选其一皆可
//    password:“123456”;  //密码
    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean>getLogin(@Field("username")String username,@Field("password")String password);
//    username:“zhangsan”;  //注册输入的用户名
//    password:“123456”;  //密码
//    phone:”13400000000”;  //手机号格式要正确
//    verify:”zwdf” //获取到的验证码
    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean>getRegister(@Field("username")String username,@Field("password")String password,@Field("verify")String verify);

}
