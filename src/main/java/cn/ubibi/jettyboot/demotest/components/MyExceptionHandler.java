package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import cn.ubibi.jettyboot.framework.rest.ifs.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class MyExceptionHandler implements ExceptionHandler {

    @Override
    public boolean handle(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {


        if("NotLogin11".equals(e.getMessage())){
            response.getWriter().println("User is Not Login ");
            response.getWriter().close();
            response.flushBuffer();

            return true;
        }

        return false;
    }
}
