package cn.ubibi.jettyboot.demotest.dao.base;


import cn.ubibi.jettyboot.framework.jdbc.DataAccessAbstract;
import cn.ubibi.jettyboot.framework.jdbc.DataAccessObject;

public abstract class MyDAO2<T> extends DataAccessAbstract<T> {
    private DataAccessObject<T> dataAccessObject;

    public MyDAO2() {
        super(MyConnectionFactory.getInstance());
    }

}