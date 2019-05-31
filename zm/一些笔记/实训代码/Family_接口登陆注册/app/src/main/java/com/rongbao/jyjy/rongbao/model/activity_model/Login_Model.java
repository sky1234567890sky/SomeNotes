package com.rongbao.jyjy.rongbao.model.activity_model;

import com.rongbao.jyjy.rongbao.apiservice.Apiservice;
import com.rongbao.jyjy.rongbao.base.BaseModel;
import com.rongbao.jyjy.rongbao.bean.Login_Bean;
import com.rongbao.jyjy.rongbao.callresult.Callresult;
import com.rongbao.jyjy.rongbao.util.ModelUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019/4/18 0018.
 */

public class Login_Model extends BaseModel {
    public void getData(final String username, final String psd, final Callresult callresult) {
        ModelUtils.getInstance().getNetWork(ModelUtils.getNetMessage(Apiservice.Zong).getLogin(username, psd), callresult);
		
		
    @POST("login")
    @FormUrlEncoded
    Observable<Login_Bean> getLogin(@Field("username") String username, @Field("password") String password);
		
    }
}
