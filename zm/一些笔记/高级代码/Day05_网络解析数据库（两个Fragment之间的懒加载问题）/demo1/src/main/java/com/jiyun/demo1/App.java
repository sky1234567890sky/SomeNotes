package com.jiyun.demo1;

import android.app.Application;

/**
 * Created by $lzj on 2019/4/4.
 */
public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

    public static App getApp() {
        return app;
    }
}
