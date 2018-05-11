package cn.ubibi.jettyboot.demotest.controller;


import cn.ubibi.jettyboot.framework.rest.annotation.DwrController;
import cn.ubibi.jettyboot.framework.rest.annotation.DwrFunction;

@DwrController
public class DwrTestController {

    @DwrFunction
    public int add(int a, int b) {
        return a + b;
    }

    @DwrFunction
    public int minus(int a, int b) {
        return a - b;
    }


    @DwrFunction
    public int multiply(int a, int b) {
        return a * b;
    }

    @DwrFunction
    public int divide(int a, int b) {
        return a / b;
    }

}

