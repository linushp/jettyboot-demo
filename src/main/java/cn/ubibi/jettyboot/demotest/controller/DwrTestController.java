package cn.ubibi.jettyboot.demotest.controller;


import cn.ubibi.jettyboot.demotest.dao.UserDAO;
import cn.ubibi.jettyboot.demotest.entity.UserEntity;
import cn.ubibi.jettyboot.demotest.entity.UserExternData;
import cn.ubibi.jettyboot.framework.ioc.Autowired;
import cn.ubibi.jettyboot.framework.rest.annotation.RpcController;
import cn.ubibi.jettyboot.framework.rest.annotation.RpcFunction;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

@RpcController
public class DwrTestController {

    @Autowired
    private UserDAO userDAO;

    @RpcFunction
    public UserEntity findById(int id) throws Exception {
        return userDAO.findById(id);
    }


    @RpcFunction
    public int add(int [][] arr) {
        int result = 0;
        for (int i = 0 ; i < arr.length ; i++){
            int [] arr2 = arr[i];
            for (int j=0;j<arr2.length ;j++){
                int a = arr2[j];
                result=result + a;
            }
        }
        return result;
    }

   @RpcFunction
    public int minus(Integer a, char b) {
        return a - b;
    }


   @RpcFunction
    public int multiply(int a, int b) {
        return a * b;
    }

   @RpcFunction
    public int divide(int a, int b) {
        return a / b;
    }


   @RpcFunction
    public int testMap(Map<String,Map<String,Queue<Integer>>> map, JSONObject jsonObject, Set<Integer> integers){
        Map<String, Queue<Integer>> a = map.get("a");
        Queue<Integer> m = a.get("m");
        m.add(323);
        return map.size() + jsonObject.size() + integers.size();
    }

   @RpcFunction
    public int testJavaObject(List dd){
        return dd.size();
    }
}
