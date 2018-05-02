package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.demotest.controller.parser.CurrentUser;
import cn.ubibi.jettyboot.framework.rest.ControllerRequest;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import cn.ubibi.jettyboot.framework.rest.ifs.ControllerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


@Component
public class MyRequestAspect implements ControllerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerInterceptor.class);

    public void invokeBefore(Method method, ControllerRequest request) throws Exception {

        request.getServletResponse().setHeader("Server", "boot " + System.currentTimeMillis());
        request.getServletResponse().setCharacterEncoding("utf-8");
        request.getServletRequest().setCharacterEncoding("utf-8");


        LOGGER.info(method.getName());
        String token = request.getCookieValue("token");
        if (token == null || token.isEmpty()) {
//                    throw new NotLoginException();
        }
        request.setAspectVariable("currentUser", new CurrentUser(token));
    }


    public void invokeAfter(Method method, ControllerRequest request, Object invokeResult) throws Exception {
        LOGGER.info(request.getPathInfo() + ":::::::::" + method.getDeclaringClass().getName() + "."+ method.getName());
    }

}
