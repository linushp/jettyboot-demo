package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.controller.render.PageRender;
import cn.ubibi.jettyboot.demotest.dao.base.MyConnectionFactory;
import cn.ubibi.jettyboot.framework.commons.FrameworkConfig;
import cn.ubibi.jettyboot.framework.rest.JettyBootServer;


import org.eclipse.jetty.server.session.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;


public class MainServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServer.class);


    public static void main(String[] args) throws Exception {


        MyConnectionFactory.getInstance().init();
        FrameworkConfig.getInstance().addConnectionFactory(MyConnectionFactory.getInstance());
        PageRender.init();


        JettyBootServer server = new JettyBootServer(8001);

        server.setControllerContext("/");
        server.setServerName("MainServer");
        server.addBean(new NullSessionCacheFactory());
        server.addBean(getJDBCSessionDataStoreFactory());

        server.doScanPackage(MainServer.class);
        server.startAndJoin();
    }


    private static JDBCSessionDataStoreFactory getJDBCSessionDataStoreFactory() {

        DataSource dataSource = MyConnectionFactory.getInstance().getDataSource();

        JDBCSessionDataStoreFactory jdbcSessionDataStoreFactory = new JDBCSessionDataStoreFactory();
        DatabaseAdaptor databaseAdaptor = new DatabaseAdaptor();
        databaseAdaptor.setDatasource(dataSource);
        jdbcSessionDataStoreFactory.setDatabaseAdaptor(databaseAdaptor);

        return jdbcSessionDataStoreFactory;
    }

}
