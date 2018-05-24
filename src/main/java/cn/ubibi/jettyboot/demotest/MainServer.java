package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.controller.render.PageRender;
import cn.ubibi.jettyboot.demotest.dao.base.MyConnectionFactory;
import cn.ubibi.jettyboot.framework.rest.JettyBootServer;


import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServer.class);


    public static void main(String[] args) throws Exception {


        MyConnectionFactory.getInstance().init();
        PageRender.init();


        JettyBootServer server = new JettyBootServer(8001);

        server.setControllerContext("/");

        server.setServerName("MainServer");
        server.doScanPackage(MainServer.class);
        server.startAndJoin();
    }

}
