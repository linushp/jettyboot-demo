package cn.ubibi.jettyboot.demotest.configs;

public class SystemConfig {

    private static final SystemConfig INSTANCE = new SystemConfig();
    private SystemConfig(){}
    public static SystemConfig getInstance(){
        return INSTANCE;
    }



    private boolean dev = false;

    public boolean isDev() {
        return dev;
    }

    public void setDev(boolean dev) {
        this.dev = dev;
    }
}
