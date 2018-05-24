package cn.ubibi.jettyboot.demotest.components;

import cn.ubibi.jettyboot.demotest.controller.parser.CurrentUser;
import cn.ubibi.jettyboot.framework.rest.annotation.Component;
import cn.ubibi.jettyboot.framework.rest.ifs.ControllerAspect;
import cn.ubibi.jettyboot.framework.rest.ifs.HttpParsedRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


@Component
public class MyRequestAspect implements ControllerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRequestAspect.class);

    public void beforeInvoke(Method method, HttpParsedRequest request) throws Exception {

        HttpSession session = request.getSession();
        Object aaa = session.getAttribute("aaaa");


        session.setAttribute("aaaa",System.currentTimeMillis());
        request.setCharacterEncoding("utf-8");


        LOGGER.info(method.getName());
        String token = request.getCookieValue("token");
        if (token == null || token.isEmpty()) {
//                    throw new NotLoginException();
        }
        request.setAspectVariable("currentUser", new CurrentUser(token));
    }

    @Override
    public void afterInvoke(Method method, HttpParsedRequest request, Object invokeResult, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        LOGGER.info(request.getPathInfo() + ":::::::::" + method.getDeclaringClass().getName() + "."+ method.getName());
    }

    @Override
    public void afterRender(Method method, HttpParsedRequest request, Object invokeResult, HttpServletResponse response) {

    }


}
