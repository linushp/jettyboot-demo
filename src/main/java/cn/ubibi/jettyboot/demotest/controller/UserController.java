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

import cn.ubibi.jettyboot.framework.rest.annotation.*;
import cn.ubibi.jettyboot.framework.rest.ifs.RequestParser;
import com.alibaba.fastjson.JSONArray;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;


@Controller({"/user","/vip"})
public class UserController {

    private static Logger logger = Log.getLogger(UserController.class);

    @Autowired
    private UserDAO userDAO;


    @GetMapping("/helloworld/:name")
    public String helloworld(ControllerRequest request,@PathVariable("name") String name){
        return "hello world : vip " + isVip(request) + " name " + name;
    }


    private boolean isVip(ControllerRequest request){
        String target = request.getTargetPath();
        if (target.startsWith("/vip")){
            return true;
        }
        return false;
    }



    @GetMapping("/test_page")
    public PageRender getmm212_page(@RequestParam("name") String name,@AspectVariable CurrentUser currentUser) throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("name",name + "__" + this.getClass().getClassLoader().getClass().getName() + "___" + currentUser.getName());
        return new PageRender("test.html",map);
    }



    @GetMapping("/test_insert2")
    public UpdateResult getmm212(@RequestParam("name") String name) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("yaoli",123);
        map.put("dai",3);
        map.put("fid",3);
        map.put("mid",3);
        map.put("create_time",System.currentTimeMillis());
        map.put("update_time",System.currentTimeMillis());
        return userDAO.insertObject(map);
    }



    @GetMapping("/test_insert")
    public UpdateResult getmm21(@RequestParams UserInfoParser reqParser) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name","name" + System.currentTimeMillis() + "_" + Math.random());
        map.put("yaoli",123);
        map.put("dai",3);
        map.put("fid",3);
        map.put("mid",3);
        map.put("create_time",System.currentTimeMillis());
        map.put("update_time",System.currentTimeMillis());
       return userDAO.insertObject(map);
    }



    @GetMapping( "/test")
    public String getmm(UserInfoParser reqParser, ControllerRequest request, CurrentUser currentUser) throws Exception {
        new UserDAO().findAll();
        if(reqParser instanceof RequestParser){
            System.out.println("111");
        }
        return  "123---" + reqParser.getName() +"=====" + currentUser.getName();
    }




    @GetMapping(value = "/")
    public Page<UserEntity> getUserById3(ControllerRequest JBRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer pageSize = JBRequest.getRequestParam("pageSize","10").toInteger();
        Integer pageNo = JBRequest.getRequestParam("pageNo","0").toInteger();

        long t1 = System.currentTimeMillis();
        Page<UserEntity> result = userDAO.findPage(pageNo, pageSize);

        long t2 = System.currentTimeMillis();

        logger.info("::"+(t2-t1));
        return result;
    }




    @GetMapping(value = "/:uid")
    public Object getUserById(ControllerRequest params, HttpServletResponse response) throws Exception {

        String uid =  params.getPathVariable("uid").toString();
        String name = params.getRequestParam("name").toString();
        String[] names = params.getParameterValues("name");

        Cookie cookie = new Cookie("aaa","a2333");

        response.addCookie(cookie);

        return userDAO.findByUsername(uid);
    }


    @PostMapping("/new/:uid")
    public String getUserById2(ControllerRequest request) throws IOException, ServletException {
        String aaa = request.getContextPath();
        return "123saaa";
    }


    @DwrFunction
    public String getUserInfoById(Object a, Integer b, @AspectVariable CurrentUser currentUser){
        // UserController.getUserInfoById(123);
        return "getUserInfoById:  " + (a.toString()+b);
    }


    @DwrFunction
    public String getUserInfoByName(Double name, @AspectVariable CurrentUser currentUser){
        // UserController.getUserInfoById(123);
        return "getUserInfoByName:  " + name;
    }



}
