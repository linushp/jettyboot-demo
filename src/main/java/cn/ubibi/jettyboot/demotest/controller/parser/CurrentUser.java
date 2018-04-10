package cn.ubibi.jettyboot.demotest.controller.parser;

import cn.ubibi.jettyboot.framework.rest.ifs.RequestParser;
import cn.ubibi.jettyboot.framework.rest.Request;

import javax.servlet.http.HttpServletRequest;

public class CurrentUser implements RequestParser {

    private String name;

    @Override
    public void doParse(Request jettyBootJBRequest, HttpServletRequest request, String path) throws Exception {
        this.name = jettyBootJBRequest.getCookieValue("name");
        if(this.name == null){
            throw new Exception("NotLogin");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
