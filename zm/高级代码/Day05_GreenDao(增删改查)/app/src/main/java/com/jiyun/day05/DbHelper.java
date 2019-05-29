package com.jiyun.day05;

import com.jiyun.day05.dao.DaoMaster;
import com.jiyun.day05.dao.DaoSession;
import com.jiyun.day05.dao.StudentDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 */
public class DbHelper {

    //单利
    private final StudentDao studentDao;

    //数据库初始化创建
    private DbHelper(){
        //1.创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getApp(), "stu.db");

        //2.获取读写对象
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        //3.获取管理器类
        DaoSession daoSession = daoMaster.newSession();

        //4.获取表对象
        studentDao = daoSession.getStudentDao();
    }

    private static DbHelper dbHelper;

    public static DbHelper getDbHelper() {
        if (dbHelper == null){
            synchronized (DbHelper.class){
                if (dbHelper == null){
                    dbHelper = new DbHelper();
                }
            }
        }
        return dbHelper;
    }


    //插入
    public void insertAll(ArrayList<Student> list){
        studentDao.insertOrReplaceInTx(list);
    }

    public void insert(Student student){
        studentDao.insertOrReplace(student);
    }

    //删除
    public void deleteAll(){
        studentDao.deleteAll();
    }

    public void delete(Student student){
        studentDao.delete(student);
    }

    //更改
    public void updateAll(ArrayList<Student> list){
        studentDao.updateInTx(list);
    }

    public void update(Student student){
        studentDao.updateInTx(student);
    }

    //查询
    public List<Student> queryAll(){
        List<Student> list = studentDao.queryBuilder().list();
        return list;
    }

    public List<Student> query(Student student){
        List<Student> list = studentDao.queryBuilder().where(StudentDao.Properties.Name.eq(student.getName()))
                .list();

        return list;
    }
}
