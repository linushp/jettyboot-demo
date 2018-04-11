package cn.ubibi.jettyboot.demotest.controller;

import cn.ubibi.jettyboot.demotest.controller.parser.CurrentUser;
import cn.ubibi.jettyboot.demotest.controller.parser.UserInfoParser;
import cn.ubibi.jettyboot.demotest.controller.render.PageRender;
import cn.ubibi.jettyboot.demotest.dao.UserDAO;
import cn.ubibi.jettyboot.demotest.entity.UserEntity;
import cn.ubibi.jettyboot.framework.commons.model.Page;
import cn.ubibi.jettyboot.framework.ioc.Autowired;
import cn.ubibi.jettyboot.framework.jdbc.model.UpdateResult;
import cn.ubibi.jettyboot.framework.rest.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.ubibi.jettyboot.framework.rest.annotation.AspectVariable;
import cn.ubibi.jettyboot.framework.rest.annotation.GetMapping;
import cn.ubibi.jettyboot.framework.rest.annotation.PostMapping;
import cn.ubibi.jettyboot.framework.rest.annotation.RequestParams;
import cn.ubibi.jettyboot.framework.rest.ifs.RequestParser;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;


public class UserController {

    private static Logger logger = Log.getLogger(UserController.class);

    @Autowired
    private UserDAO userDAO;


    @GetMapping("/test_insert")
    public UpdateResult getmm21(@RequestParams UserInfoParser reqParser, @AspectVariable CurrentUser currentUser) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name","name" + System.currentTimeMillis() + "_" + Math.random());
        map.put("yaoli",123);
        map.put("dai",3);
        map.put("fid",3);
        map.put("mid",3);
        map.put("create_time",System.currentTimeMillis());
        map.put("update_time",System.currentTimeMillis());
       return userDAO.insertObject(map);
//        return  "123---" + reqParser.getName() +"=====" +
    }



    @GetMapping( "/test")
    public String getmm(UserInfoParser reqParser, Request request, CurrentUser currentUser) throws Exception {
        new UserDAO().findAll();
        if(reqParser instanceof RequestParser){
            System.out.println("111");
        }
        return  "123---" + reqParser.getName() +"=====" + currentUser.getName();
    }



    @GetMapping(value = "/")
    public Page<UserEntity> getUserById3(Request JBRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer pageSize = JBRequest.getRequestParam("pageSize","10").toInteger();
        Integer pageNo = JBRequest.getRequestParam("pageNo","0").toInteger();




//        logger.info("aaa");
        long t1 = System.currentTimeMillis();



        Page<UserEntity> result = userDAO.findPage(pageNo, pageSize);
//        return "hello222";

        long t2 = System.currentTimeMillis();

        logger.info("::"+(t2-t1));
        return result;
    }




    @GetMapping(value = "/:uid")
    public Object getUserById(Request params, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String uid =  params.getPathVariable("uid").toString();
        String name = params.getRequestParam("name").toString();
        String[] names = request.getParameterValues("name");


//        System.out.println("111");
//        try {
//            Thread.sleep(1000 * 10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        request.getSession(true).setAttribute("uid",uid);
//        Object mm = request.getSession(true).getAttribute("uid");

        Cookie cookie = new Cookie("aaa","a2333");
        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();

        String aaa = request.getContextPath();

        return new PageRender("getUserById",userDAO.findById(uid));

//        return userDAO.findById(uid);
//        return "hello:" + uid + ":" + name;
    }


    @PostMapping("/new/:uid")
    public String getUserById2(Request request) throws IOException, ServletException {
        String aaa = request.getContextPath();
        return "123saaa";
    }



}
