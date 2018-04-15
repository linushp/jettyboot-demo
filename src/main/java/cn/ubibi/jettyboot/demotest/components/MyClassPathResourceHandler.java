package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.framework.rest.ClassPathResourceHandler;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.ScopedHandler;
import org.eclipse.jetty.util.resource.Resource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class MyClassPathResourceHandler extends ContextHandler {
    public MyClassPathResourceHandler(){
        super("/public");
        this.setHandler(new MyClassPathResourceHandler2());
    }
}





class MyClassPathResourceHandler2 extends ClassPathResourceHandler {

    private String[] suffixList = new String[]{".js",".css",".png",".jpg",".gif",".html"};

    public MyClassPathResourceHandler2() {
        super("/public");
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        if (isSupportSuffix(target)){
            response.setHeader("Server","hello");
            super.handle(target,baseRequest,request,response);
        }
    }


    private boolean isSupportSuffix(String target){
        for (String suffix : suffixList){
            if (target.endsWith(suffix)){
                return true;
            }
        }
        return false;
    }
}
