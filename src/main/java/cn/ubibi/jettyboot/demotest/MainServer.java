package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.controller.render.PageRender;
import cn.ubibi.jettyboot.demotest.core.AsyncContextTaskManager;
import cn.ubibi.jettyboot.demotest.core.MethodInvokeCallable;
import cn.ubibi.jettyboot.demotest.dao.base.MyConnectionFactory;
import cn.ubibi.jettyboot.demotest.service.TestService;
import cn.ubibi.jettyboot.framework.commons.FrameworkConfig;
import cn.ubibi.jettyboot.framework.rest.JettyBootServer;


import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.session.*;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.concurrent.Callable;


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


        ServerConnector connector = new ServerConnector(server);
        connector.setAcceptQueueSize(100);
        connector.setPort(8001);
        server.setConnectors(new Connector[] { connector });
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
