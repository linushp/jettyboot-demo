package cn.ubibi.jettyboot.demotest.dao.base;

import cn.ubibi.jettyboot.framework.jdbc.DataAccess;
import cn.ubibi.jettyboot.framework.jdbc.DBUtils;
import cn.ubibi.jettyboot.framework.jdbc.ConnectionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyConnectionFactory implements ConnectionFactory {

    private static MyConnectionFactory ourInstance = new MyConnectionFactory();

    public static MyConnectionFactory getInstance() {
        return ourInstance;
    }

    private DataSource dataSource ;



    private MyConnectionFactory() {
    }


    public void init(){
        this.dataSource = DBUtils.createComboPooledDataSource("c3p0.properties");
        try {
            Connection conn = this.dataSource.getConnection();
            DataAccess dataAccess = DataAccess.use(conn);
            dataAccess.query("SELECT now()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
