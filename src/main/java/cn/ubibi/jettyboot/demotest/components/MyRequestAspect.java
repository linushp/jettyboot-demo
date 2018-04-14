package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.demotest.MainServer;
import cn.ubibi.jettyboot.demotest.controller.parser.CurrentUser;
import cn.ubibi.jettyboot.framework.rest.Request;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import cn.ubibi.jettyboot.framework.rest.ifs.RequestAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


@Component
public class MyRequestAspect implements RequestAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAspect.class);

    public void invokeBefore(Method method, Request request) throws Exception {

        request.getServletResponse().setHeader("Server", "hello world");
        request.getServletResponse().setCharacterEncoding("utf-8");
        request.getServletRequest().setCharacterEncoding("utf-8");


        LOGGER.info(method.getName());
        String token = request.getCookieValue("token");
        if (token == null || token.isEmpty()) {
//                    throw new NotLoginException();
        }
        request.setAspectVariable("currentUser", new CurrentUser(token));
    }


    public void invokeAfter(Method method, Request request, Object invokeResult) throws Exception {
        LOGGER.info(method.getName());
    }

}
