package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.framework.rest.ClassPathResourceHandler;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class MyClassPathResourceHandler extends ClassPathResourceHandler {


    private String[] suffixList = new String[]{".js",".css",".png",".jpg",".gif",".html"};

    public MyClassPathResourceHandler() throws IOException {
        super("public");
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        if (isSupportSuffix(target)){

            String target2 = target;

//            target2 = target.replaceFirst("/public","");
//            request.setAttribute(RequestDispatcher.INCLUDE_REQUEST_URI,true);
//            request.setAttribute(RequestDispatcher.INCLUDE_SERVLET_PATH,"");
//            request.setAttribute(RequestDispatcher.INCLUDE_PATH_INFO,target2);


            super.handle(target2,baseRequest,request,response);
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
