package com.jiyun.demo1;

import com.jiyun.demo1.dao.DaoMaster;
import com.jiyun.demo1.dao.DaoSession;
import com.jiyun.demo1.dao.PersonDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by $lzj on 2019/4/4.
 */
public class DbUtils {

    private static DbUtils dbUtils;
    private final PersonDao personDao;

    private DbUtils(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getApp(), "person" +
                ".db");

        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        DaoSession daoSession = daoMaster.newSession();

        personDao = daoSession.getPersonDao();
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

    public void insertAll(ArrayList<Person> list){
        if (has()){
            return;
        }
        personDao.insertOrReplaceInTx(list);
    }

    public void insert(Person person){
        personDao.insertOrReplace(person);
    }

    public void delete(Person person){
        personDao.delete(person);
    }

    public void update(Person person){
        personDao.update(person);
    }

    public List<Person> query(){
        List<Person> list = personDao.queryBuilder().list();

        return list;
    }

    public List<Person> queryPerson(String name){
        List<Person> list = personDao.queryBuilder().where(PersonDao.Properties.Name.eq(name)).list();

        return list;
    }

    public boolean has(){
        List<Person> list = personDao.queryBuilder().list();
        if (list!=null&&list.size()>0){
            return true;
        }

        return false;
    }
}
