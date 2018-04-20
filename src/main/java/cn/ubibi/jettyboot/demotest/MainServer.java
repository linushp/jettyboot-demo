package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.controller.render.PageRender;
import cn.ubibi.jettyboot.demotest.dao.base.MyConnectionFactory;
import cn.ubibi.jettyboot.framework.rest.ClassPathResourceHandler;
import cn.ubibi.jettyboot.framework.rest.ControllerContextHandler;
import cn.ubibi.jettyboot.framework.rest.JettyBootServer;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServer.class);


    public static void main(String[] args) throws Exception {


        MyConnectionFactory.getInstance().init();
        PageRender.init();


        JettyBootServer server = new JettyBootServer(MainServer.class);

        server.doScanPackage();

        server.listen(8001);

    }

}
