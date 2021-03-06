package com.rongbao.jyjy.rongbao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.rongbao.jyjy.rongbao.bean.MyDB_Login_Bean;

import com.rongbao.jyjy.rongbao.db.MyDB_Login_BeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig myDB_Login_BeanDaoConfig;

    private final MyDB_Login_BeanDao myDB_Login_BeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        myDB_Login_BeanDaoConfig = daoConfigMap.get(MyDB_Login_BeanDao.class).clone();
        myDB_Login_BeanDaoConfig.initIdentityScope(type);

        myDB_Login_BeanDao = new MyDB_Login_BeanDao(myDB_Login_BeanDaoConfig, this);

        registerDao(MyDB_Login_Bean.class, myDB_Login_BeanDao);
    }
    
    public void clear() {
        myDB_Login_BeanDaoConfig.clearIdentityScope();
    }

    public MyDB_Login_BeanDao getMyDB_Login_BeanDao() {
        return myDB_Login_BeanDao;
    }

}
