package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.controller.render.PageRender;
import cn.ubibi.jettyboot.demotest.dao.base.MyConnectionFactory;

import cn.ubibi.jettyboot.framework.commons.scan.PackageScanner;
import cn.ubibi.jettyboot.framework.rest.JettyBootServer;


import net.sf.cglib.proxy.Enhancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MainServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServer.class);


    public static void main(String[] args) throws Exception {


        MyConnectionFactory.getInstance().init();
        PageRender.init();



        JettyBootServer server = new JettyBootServer();
        server.doScanPackage(MainServer.class);
        server.listen(8001);

    }

}
