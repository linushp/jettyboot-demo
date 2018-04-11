package cn.ubibi.jettyboot.demotest.controller.parser;

import cn.ubibi.jettyboot.framework.rest.ifs.RequestParser;
import cn.ubibi.jettyboot.framework.rest.Request;

import javax.servlet.http.HttpServletRequest;

public class CurrentUser {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
