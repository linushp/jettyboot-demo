package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.framework.rest.ResourceHandlers;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;

@Component
public class MyClassPathResourceHandler extends ResourceHandlers.ContextClassPathResourceHandler {
    public MyClassPathResourceHandler(){
        super("/public");
    }
}