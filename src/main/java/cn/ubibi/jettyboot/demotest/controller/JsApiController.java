package cn.ubibi.jettyboot.demotest.controller;

import cn.ubibi.jettyboot.framework.ioc.Autowired;
import cn.ubibi.jettyboot.framework.rest.ControllerMethodHandler;
import cn.ubibi.jettyboot.framework.rest.ControllerContextHandler;
import cn.ubibi.jettyboot.framework.rest.annotation.Controller;
import cn.ubibi.jettyboot.framework.rest.annotation.GetMapping;
import cn.ubibi.jettyboot.framework.rest.annotation.PathVariable;
import cn.ubibi.jettyboot.framework.rest.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/jsApi")
public class JsApiController {


    @Autowired
    private ControllerContextHandler context;


    @GetMapping("/")
    public Object xxx(@RequestParam("controller") String controller){
        List<ControllerMethodHandler> methods = context.getControllerMethodHandlers();

        String contextPath = context.getContextPath();


        List<Api> apis = new ArrayList<>();

        for (ControllerMethodHandler methodHandler:methods){


            String methodName = methodHandler.getMethod().getName();

            String url = methodHandler.getTargetPath();
            if (!"/".equals(contextPath)){
                url = contextPath + url;
            }

            if (controller.equals(methodHandler.getMethod().getDeclaringClass().getSimpleName())){
                apis.add(new Api(methodHandler.getSupportRequestMethod(),url,methodName));
            }


        }

        return apis;
    }



    public static class Api{
        private String method;
        private String url;
        private String func;

        public Api(String annotationMethod, String url, String functionName) {
            this.method = annotationMethod;
            this.url = url;
            this.func = functionName;
        }


        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFunc() {
            return func;
        }

        public void setFunc(String func) {
            this.func = func;
        }
    }
}

