package com.jiyun.demo2.utils;

import com.jiyun.demo2.base.MyApp;
import com.jiyun.demo2.bean.DbBean;
import com.jiyun.demo2.dao.DaoMaster;
import com.jiyun.demo2.dao.DaoSession;
import com.jiyun.demo2.dao.DbBeanDao;

import java.util.List;

/**
 * Created by $lzj on 2019/4/8.
 */
public class DbUtils {

    private static DbUtils dbUtils;
    private final DbBeanDao dbBeanDao;

    private DbUtils(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApp.getApp(), "info" +
                ".db");

        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        DaoSession daoSession = daoMaster.newSession();

        dbBeanDao = daoSession.getDbBeanDao();
    }

    public static DbUtils getDbUtils() {
        if (dbUtils == null){
            synchronized (DbUtils.class){
                if (dbUtils == null){
                    dbUtils = new DbUtils();
                }
            }
        }
        return dbUtils;
    }

    public void insert(DbBean dbBean){
        if (has(dbBean)){
            return;
        }

        dbBeanDao.insertOrReplace(dbBean);
    }

    public void delete(DbBean dbBean){
        if (has(dbBean)){
            dbBeanDao.delete(dbBean);
        }
    }

    public List<DbBean> query(){
        return  dbBeanDao.queryBuilder().list();
    }

    public boolean has(DbBean dbBean){
        List<DbBean> list = dbBeanDao.queryBuilder().where(DbBeanDao.Properties.Text.eq(dbBean
                .getText()), DbBeanDao.Properties.Thumbnail.eq(dbBean.getThumbnail())).list();

        if(list!=null&&list.size()>0){
            return true;
        }

        return false;
    }
}
