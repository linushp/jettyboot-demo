package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.controller.render.PageRender;
import cn.ubibi.jettyboot.demotest.dao.base.MyConnectionFactory;
import cn.ubibi.jettyboot.framework.rest.RestContextHandler;
import org.eclipse.jetty.server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServer.class);


    public static void main(String[] args) throws Exception {



        MyConnectionFactory.getInstance().init();
        PageRender.init();



        RestContextHandler context = new RestContextHandler("/");
        context.addByPackageScanner(MainServer.class.getPackage().getName());



        Server server = new Server(8001);
        server.setHandler(context);
        server.start();
        server.join();

    }

}
