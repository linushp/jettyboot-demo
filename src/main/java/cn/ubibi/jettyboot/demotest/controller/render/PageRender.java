package cn.ubibi.jettyboot.demotest.controller.render;
import cn.ubibi.jettyboot.framework.rest.ifs.ResponseRender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PageRender implements ResponseRender{

    private static TemplateEngine templateEngine;

    static {
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setTemplateMode("XHTML");
//        templateResolver.setPrefix("/WEB-INF/templates/");
//        templateResolver.setSuffix(".html");
//        FileTemplateResolver templateResolver = new FileTemplateResolver();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver(PageRender.class.getClassLoader());
//        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("/templates/");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(false);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }


    private String template;
    private Object pageData;
    public PageRender(String template, Object pageData) {
        this.pageData = pageData;
        this.template = template;
    }

    @Override
    public void doRender(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletContext servletContext = request.getServletContext();
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        Map<String, Object> variables = objectToMap(this.pageData);
        ctx.setVariables(variables);

        response.setCharacterEncoding("utf-8");
        templateEngine.process(this.template, ctx, response.getWriter());
    }


    private static Map<String, Object> objectToMap(Object obj) {
        if(obj == null){
            return null;
        }

        if(obj instanceof Map){
            return (Map<String, Object>) obj;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

}
