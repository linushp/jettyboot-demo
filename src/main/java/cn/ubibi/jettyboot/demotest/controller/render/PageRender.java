package cn.ubibi.jettyboot.demotest.controller.render;

import cn.ubibi.jettyboot.demotest.configs.SystemConfig;
import cn.ubibi.jettyboot.framework.commons.ResponseUtils;
import cn.ubibi.jettyboot.framework.commons.StringUtils;
import cn.ubibi.jettyboot.framework.rest.ifs.ResponseRender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PageRender implements ResponseRender {

    private static TemplateEngine templateEngine = null;

    static {
        init();
    }


    public static void init() {
        if (templateEngine == null) {
            templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(getTemplateResolver());
        }
    }


    private static ITemplateResolver getTemplateResolver() {
        if (SystemConfig.getInstance().isDev()) {
            return getFileTemplateResolver();
        }
        return getClassLoaderTemplateResolver();
    }


    private static FileTemplateResolver getFileTemplateResolver() {

        URL classLoaderResource = PageRender.class.getClassLoader().getResource("");
        String classPath = StringUtils.appendIfNotEnd(classLoaderResource.getPath(), File.separator);
        String srcResPath = classPath + ("../../src/main/resources".replace("/", File.separator));


        FileTemplateResolver templateResolver = new FileTemplateResolver();

        templateResolver.setSuffix(".html");
        templateResolver.setPrefix(srcResPath + "/templates/");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(false);
        return templateResolver;
    }


    private static ClassLoaderTemplateResolver getClassLoaderTemplateResolver() {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver(PageRender.class.getClassLoader());

        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("/templates/");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(true);

        return templateResolver;
    }





    private static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof Map) {
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


        templateEngine.process(this.template, ctx, response.getWriter());

        ResponseUtils.tryClose(response);
    }

}
