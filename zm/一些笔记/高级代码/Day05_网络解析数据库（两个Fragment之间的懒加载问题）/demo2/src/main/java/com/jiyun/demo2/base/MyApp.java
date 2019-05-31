package com.jiyun.demo2.base;

import android.app.Application;

/**
 * Created by $lzj on 2019/4/8.
 */
public class MyApp extends Application {

    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

    public static MyApp getApp() {
        return app;
    }
}
