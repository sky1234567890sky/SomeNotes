package com.jiyun.day05;

import android.app.Application;

import com.jiyun.day05.dao.DaoMaster;
import com.jiyun.day05.dao.DaoSession;
import com.jiyun.day05.dao.StudentDao;

/**
 * Created by $lzj on 2019/4/4.
 */
public class MyApp extends Application {

    private StudentDao studentDao;
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();

        //1.返回整个应用程序的上下文对象
        app = this;

        //2.初始化数据库
        createDatabase();
    }

    //1.返回上下文对象
    public static MyApp getApp() {
        return app;
    }

    //初始化创建数据库
    private void createDatabase() {
        //1.创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "student.db");

        //2.获取读写对象
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        //3.获取管理器类
        DaoSession daoSession = daoMaster.newSession();

        //4.获取表对象
        studentDao = daoSession.getStudentDao();
    }

    //返回studentDao对象
    public StudentDao getStudentDao() {
        return studentDao;
    }
}
