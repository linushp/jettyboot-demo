package cn.ubibi.jettyboot.demotest.controller;

import cn.ubibi.jettyboot.framework.rest.ControllerRequest;
import cn.ubibi.jettyboot.framework.rest.annotation.Controller;
import cn.ubibi.jettyboot.framework.rest.annotation.GetMapping;

import java.util.Base64;

@Controller({"/","/home","/index","welcome"})
public class HomeController {

    @GetMapping({"/","index.html","index2.html"})
    public String helloHome(ControllerRequest request){

        return  "welcome home:" + request.getPathInfo() + "__" + request.getTargetPath();
    }
}