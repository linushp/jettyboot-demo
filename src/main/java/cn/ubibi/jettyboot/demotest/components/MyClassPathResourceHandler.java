package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.framework.rest.ClassPathResourceHandler;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;

@Component
public class MyClassPathResourceHandler extends ClassPathResourceHandler {

    public MyClassPathResourceHandler() {
        super("/public/");
    }

}
