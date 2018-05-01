package cn.ubibi.jettyboot.demotest.dao.base;

import cn.ubibi.jettyboot.framework.commons.PropertiesUtils;
import cn.ubibi.jettyboot.framework.jdbc.DataAccess;
import cn.ubibi.jettyboot.framework.jdbc.ConnectionFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

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



        try {


            MyC3P0Config config = PropertiesUtils.getBeanByProperties("my_c3p0.properties",MyC3P0Config.class);


            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            dataSource.setTestConnectionOnCheckin(true);
            dataSource.setTestConnectionOnCheckout(true);
            dataSource.setAutomaticTestTable("c3p0_test");
            dataSource.setIdleConnectionTestPeriod(60); // 60秒
            dataSource.setMaxIdleTime(180);
            dataSource.setCheckoutTimeout(5 * 1000);//5 * 1000 ms


            dataSource.setDriverClass(config.getDriverClass());
            dataSource.setJdbcUrl(config.getJdbcUrl());
            dataSource.setUser(config.getUser());
            dataSource.setPassword(config.getPassword());
            dataSource.setInitialPoolSize(config.getInitialPoolSize());
            dataSource.setMaxPoolSize(config.getMaxPoolSize());
            dataSource.setMinPoolSize(config.getMinPoolSize());
            dataSource.setMaxStatements(config.getMaxStatements());



            this.dataSource = dataSource;
            Connection conn = this.dataSource.getConnection();
            DataAccess dataAccess = DataAccess.use(conn);
            Object m = dataAccess.queryValue("SELECT now()");
            LOGGER.info("SELECT now():" + m.toString());
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
