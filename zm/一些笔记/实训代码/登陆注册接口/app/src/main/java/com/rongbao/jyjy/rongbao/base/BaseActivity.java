package com.rongbao.jyjy.rongbao.base;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.rongbao.jyjy.rongbao.bean.Register_Bean;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/4/15 0015.
 */

public abstract  class BaseActivity<P extends BasePresent,V extends BaseView> extends AppCompatActivity {

    protected P basepresnet;
    protected FragmentManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   //获取 布局的方法
        setContentView(getLayout());
         ButterKnife.bind(this);  // 找到布局之后然后注册butter
        initPer();
        basepresnet = getPresent();
        if(basepresnet!=null){
            basepresnet.bind((V)this);
        }


    manager = getSupportFragmentManager();
    initview();   //找布局
    initClick();   //点击事件
    initother();
    initData();
    }

    protected void initData() {

    }


    protected void initother() {

    }
    private void initPer() {
        String[] per = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
                ,Manifest.permission.CALL_PHONE
               ,Manifest.permission.WRITE_SETTINGS};
        ActivityCompat.requestPermissions(this, per, 100);
    }
    protected abstract int getLayout();

    protected void initClick() {

    }

    protected void initview() {

    }

    protected abstract P getPresent();  //获取p层

}
