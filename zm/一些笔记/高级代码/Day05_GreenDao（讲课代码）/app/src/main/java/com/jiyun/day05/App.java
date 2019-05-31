package com.jiyun.day05;

import android.app.Application;

/**
 * Created by $lzj on 2019/4/4.
 * 清单文件配置声明
 */
public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        //返回上下文对象
        app = this;
    }

    public static App getApp() {
        return app;
    }
}
