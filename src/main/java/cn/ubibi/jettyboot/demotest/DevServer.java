package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.configs.SystemConfig;


public class DevServer {

    public static void main(String[] args) throws Exception {

        SystemConfig.getInstance().setDev(true);
        SystemConfig.getInstance().setSrcbase("/Users/luan/github/jettyboot-demo/src");

        MainServer.main(args);
    }
}
