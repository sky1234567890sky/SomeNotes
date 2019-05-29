package com.example.zhoutiandemo.db;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.zhoutiandemo.dao.DaoMaster;
import com.example.zhoutiandemo.dao.DaoSession;
import com.example.zhoutiandemo.dao.ShuJuKuDemoDao;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/*
 *         王董辉
 *                             class
 */
public class BaseApp extends Application {
    public static BaseApp baseApp;
    private ShuJuKuDemoDao shuJuKuDemoDao;

    @Override
    public void onCreate() {
        super.onCreate();

        baseApp = this;

        oncreateDataBase();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);


        UMConfigure.init(this, "5cb2df7d3fc195d7eb00116b"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0


        //todo 三方ak没有申请
        ///需要设置各个平台的appkey：
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("2248884681", "7ed722f6244fb5ce4504b4fe46c394bb",
                "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    private void oncreateDataBase() {

        //创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "mydb.db");

        //获取读写对象
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        //获取管理器类
        DaoSession daoSession = daoMaster.newSession();

        //获取表对象
        shuJuKuDemoDao = daoSession.getShuJuKuDemoDao();

    }

    public static BaseApp getBaseApp() {
        return baseApp;
    }

    public ShuJuKuDemoDao getShuJuKuDemoDao() {
        return shuJuKuDemoDao;
    }
}
