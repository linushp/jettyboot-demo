package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.framework.rest.ResourceHandlers;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import cn.ubibi.jettyboot.framework.rest.ifs.ResourceHandlerFilter;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyClassPathResourceHandler extends ResourceHandlers.ContextClassPathResourceHandler {
    public MyClassPathResourceHandler(){
        super("/public");
    }
}