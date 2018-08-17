package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.demotest.configs.SystemConfig;
import cn.ubibi.jettyboot.framework.rest.handlers.ResourceHandlers;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import cn.ubibi.jettyboot.framework.rest.annotation.ComponentFactory;
import org.eclipse.jetty.server.handler.ContextHandler;


@ComponentFactory
public class MyClassPathResourceHandler {

    @Component
    public ContextHandler getResourceContextHandler() {
        if (SystemConfig.getInstance().isDev()) {
            String srcResPath = SystemConfig.getInstance().getSrcbase() + "/main/resources/public";
            return new ResourceHandlers.ContextFileSystemResourceHandler("/public", srcResPath);
        } else {
            return new ResourceHandlers.ContextClassPathResourceHandler("/public");
        }
    }

}