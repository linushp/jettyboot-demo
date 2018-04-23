package cn.ubibi.jettyboot.demotest.controller;

import cn.ubibi.jettyboot.framework.rest.ControllerRequest;
import cn.ubibi.jettyboot.framework.rest.annotation.Controller;
import cn.ubibi.jettyboot.framework.rest.annotation.GetMapping;

import java.util.Base64;

@Controller({"/","/home","/index","welcome","w1","w2","w3","w4","w5","w6","w7","w8","w9","w10"})
public class HomeController {

    @GetMapping({"/","index.html","index2.html","x1","x2","x3","x4","x5","x6","x7","x8","x9","x10","x11","x12"})
    public String helloHome(ControllerRequest request){

        return  "welcome home:" + request.getPathInfo() + "__" + request.getTargetPath() + "____" + System.currentTimeMillis();
    }
}