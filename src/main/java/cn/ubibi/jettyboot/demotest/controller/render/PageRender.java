package cn.ubibi.jettyboot.demotest.controller.render;

import cn.ubibi.jettyboot.framework.rest.ifs.ResponseRender;
import cn.ubibi.jettyboot.framework.rest.impl.TextRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageRender implements ResponseRender {

    public PageRender(String pageUri, Object pageData) {
    }

    @Override
    public void doRender(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new TextRender("hello page ").doRender(request,response);
    }
}
