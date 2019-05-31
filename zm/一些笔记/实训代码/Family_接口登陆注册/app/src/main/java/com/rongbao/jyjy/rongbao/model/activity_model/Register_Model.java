package com.rongbao.jyjy.rongbao.model.activity_model;

import android.graphics.ColorSpace;

import com.bumptech.glide.load.model.ModelLoader;
import com.rongbao.jyjy.rongbao.apiservice.Apiservice;
import com.rongbao.jyjy.rongbao.bean.Register_Bean;
import com.rongbao.jyjy.rongbao.bean.Veridity_Bean;
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
 * Created by Administrator on 2019/4/19 0019.
 */

public class Register_Model {
    public void getData(String name, String psd, String phone, String verifi,final Callresult callresult) {
        ModelUtils.getInstance().getNetWork(ModelUtils.getNetMessage(Apiservice.Zong).getRegister(name,psd,phone,verifi),callresult);
    }
    public void getVerifity(final Callresult callresult) {
        ModelUtils.getInstance().getNetWork(ModelUtils.getNetMessage(Apiservice.Zong).getVerifity(),callresult);
    }
}
