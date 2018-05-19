package cn.ubibi.jettyboot.demotest.controller;


import cn.ubibi.jettyboot.demotest.service.EosCompileService;
import cn.ubibi.jettyboot.framework.ioc.Autowired;
import cn.ubibi.jettyboot.framework.jdbc.model.UpdateResult;
import cn.ubibi.jettyboot.framework.rest.annotation.DwrController;
import cn.ubibi.jettyboot.framework.rest.annotation.DwrFunction;

import java.io.*;

@DwrController
public class DwrEosController {

    @Autowired
    private EosCompileService eosCompileService;


    @DwrFunction
    public String getCodeByName(String name) throws Exception {
        return eosCompileService.getCodeByName(name);
    }


    @DwrFunction
    public Object[] saveAsFile(String name, String code) throws Exception {
        File obj1 = eosCompileService.saveAsFile(name, code);
        UpdateResult obj2 = eosCompileService.saveToDataBase(name, code);
        return new Object[]{obj1.getAbsolutePath(), obj2};
    }


    @DwrFunction
    public String compileToAbi(String name) throws Exception {
        String folder = eosCompileService.getFolder(name);
        return eosCompileService.compileToAbi(folder, name);
    }


    @DwrFunction
    public String compileToWast(String name) throws Exception {
        String folder = eosCompileService.getFolder(name);
        return eosCompileService.compileToWast(folder, name);
    }


    @DwrFunction
    public String[] createContractAccount() throws IOException, InterruptedException {
        String account_name = EosAccountUtils.getEosRandomAccountName();
        String consoleStr = eosCompileService.createContractAccount(account_name);
        return new String[]{account_name, consoleStr};
    }


    @DwrFunction
    public String deployContract(String name, String contract_account_name) throws IOException, InterruptedException {
        String folder = eosCompileService.getFolder(name);
        return eosCompileService.deployContract(folder, contract_account_name);
    }


}
