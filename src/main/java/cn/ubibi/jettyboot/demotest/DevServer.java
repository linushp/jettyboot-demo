package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.configs.SystemConfig;

public class DevServer {
    public static void main(String[] args) throws Exception {

        SystemConfig.getInstance().setDev(true);


        MainServer.main(args);
    }
}