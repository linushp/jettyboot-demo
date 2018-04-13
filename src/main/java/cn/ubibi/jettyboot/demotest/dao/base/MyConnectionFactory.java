package cn.ubibi.jettyboot.demotest.dao.base;

import cn.ubibi.jettyboot.framework.jdbc.DataAccess;
import cn.ubibi.jettyboot.framework.jdbc.DBUtils;
import cn.ubibi.jettyboot.framework.jdbc.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyConnectionFactory implements ConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyConnectionFactory.class);


    private static MyConnectionFactory ourInstance = new MyConnectionFactory();

    public static MyConnectionFactory getInstance() {
        return ourInstance;
    }

    private DataSource dataSource;


    private MyConnectionFactory() {
    }


    public void init() {
        this.dataSource = DBUtils.createComboPooledDataSource("c3p0.properties");
        try {
            Connection conn = this.dataSource.getConnection();
            DataAccess dataAccess = DataAccess.use(conn);
            Object m = dataAccess.queryValue("SELECT now()");
            LOGGER.info("SELECT now():" + m.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
