package com.example.myfilemvpdomo.api;

import com.example.myfilemvpdomo.beans.ArtDatas;
import com.example.myfilemvpdomo.beans.BeannerDatas;
import com.example.myfilemvpdomo.beans.UploadImage;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by 苏克阳 on 2019/4/23.
 */

public interface MyServer {
    // http://www.wanandroid.com/article/list/0/json
    // http://www.wanandroid.com/banner/json

    String path = "http://www.wanandroid.com/";

    @GET("article/list/{page}/json")
    Observable<ArtDatas>ArtData(@Path("page") int page);

    @GET("banner/json")
    Observable<BeannerDatas>BeannerDatas();


    //上传
    String URLupdata = "http://yun918.cn/study/public/";
    @Multipart
    @POST("file_upload.php")
    Observable<UploadImage>get(@Part("key") RequestBody key, @Part MultipartBody.Part body);

    String downLoad = "http://cdn.banmi.com/banmiapp/";
    @GET("apk/banmi_330.apk")
    Observable<ResponseBody>download();
}
