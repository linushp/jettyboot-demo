package cn.ubibi.jettyboot.demotest.service;

public class TestService{
        public String testLongTime() {
            try {
                System.out.println("TestService:testLongTime,called");
                Thread.sleep(10000);
                return "ok";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "error";
        }
    }
