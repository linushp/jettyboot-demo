package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.demotest.configs.SystemConfig;
import cn.ubibi.jettyboot.framework.rest.ResourceHandlers;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import cn.ubibi.jettyboot.framework.rest.annotation.ComponentFactory;
import cn.ubibi.jettyboot.framework.rest.ifs.ResourceHandlerFilter;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ContextHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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