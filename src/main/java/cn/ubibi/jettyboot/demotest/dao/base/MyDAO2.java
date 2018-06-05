package cn.ubibi.jettyboot.demotest.dao.base;


import cn.ubibi.jettyboot.framework.jdbc.DataAccessAbstract;
import cn.ubibi.jettyboot.framework.jdbc.DataAccessObject;

import java.util.Map;

public abstract class MyDAO2<T> extends DataAccessAbstract<T> {


    public MyDAO2() {
        super(MyConnectionFactory.getInstance());
    }

    public void insert(String tableName, T entity) throws Exception {
        DataAccessObject<T> dataAccessObject = new DataAccessObject<>(null, tableName, MyConnectionFactory.getInstance());
        dataAccessObject.insertObject(entity);
    }

    public void insert(String tableName, Map<String,Object> entity) throws Exception {
        DataAccessObject<T> dataAccessObject = new DataAccessObject<>(null, tableName, MyConnectionFactory.getInstance());
        dataAccessObject.insertObject(entity);
    }

}