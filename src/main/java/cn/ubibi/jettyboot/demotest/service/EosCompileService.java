package cn.ubibi.jettyboot.demotest.service;

import cn.ubibi.jettyboot.demotest.dao.EosContractAbiDAO;
import cn.ubibi.jettyboot.demotest.dao.EosContractCodeDAO;
import cn.ubibi.jettyboot.demotest.dao.EosContractWastDAO;
import cn.ubibi.jettyboot.demotest.entity.EosContractAbiEntity;
import cn.ubibi.jettyboot.demotest.entity.EosContractCodeEntity;
import cn.ubibi.jettyboot.demotest.entity.EosContractWastEntity;
import cn.ubibi.jettyboot.framework.commons.BeanUtils;
import cn.ubibi.jettyboot.framework.ioc.Autowired;
import cn.ubibi.jettyboot.framework.jdbc.model.UpdateResult;
import cn.ubibi.jettyboot.framework.rest.annotation.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class EosCompileService {

    private static final String public_key = "EOS7Gut6Q5acccEYNigTsbjuWwPjJ6DwBb6L237NotC8LdBvCSeKJ";


    private static final String base_folder = "/Users/luan/temp/eos_contract/";


    @Autowired
    private EosContractCodeDAO eosContractCodeDAO;

    @Autowired
    private EosContractWastDAO eosContractWastDAO;

    @Autowired
    private EosContractAbiDAO eosContractAbiDAO;


    public String createContractAccount(String contract_account_name) throws IOException, InterruptedException {
        String cmd = "cleos create account eosio " + contract_account_name + " " + public_key + " " + public_key;
        return executeCommand(cmd);
    }


    public String compileToWast(String folder, String name) throws Exception {


        String cpp = folder + "/" + name + ".cpp";
        String wast = folder + "/" + name + ".wast";

        String cmd = "eosiocpp -o " + wast + " " + cpp;


        String cmdResult = executeCommand(cmd);


        try {

            EosContractWastEntity entity = new EosContractWastEntity();
            entity.setCode_name(name);
            entity.setWast(readToString(wast));
            eosContractWastDAO.insertObject(entity);

        } catch (Exception e) {

        }

        return cmdResult;
    }


    public String compileToAbi(String folder, String name) throws Exception {

        String cpp = folder + "/" + name + ".cpp";
        String abi = folder + "/" + name + ".abi";

        String cmd = "eosiocpp -g " + abi + " " + cpp;

        String x = executeCommand(cmd);


        try {
            EosContractAbiEntity entity = new EosContractAbiEntity();
            entity.setCode_name(name);
            entity.setAbi(readToString(abi));
            eosContractAbiDAO.insertObject(entity);
        } catch (Exception e) {

        }


        return x;
    }


    public String deployContract(String folder, String contract_account_name) throws IOException, InterruptedException {
        String cmd = "cleos set contract " + contract_account_name + "  " + folder + " -p " + contract_account_name;
        return executeCommand(cmd);
    }


    private String executeCommand(String cmd) throws IOException, InterruptedException {

        StringBuffer sb = new StringBuffer();

        Process p;
        try {
            //执行命令
            p = Runtime.getRuntime().exec(cmd);
            //取得命令结果的输出流
            //输出流
            InputStream fis = p.getInputStream();
            //错误流
            InputStream ferrs = p.getErrorStream();
            //用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(fis);
            InputStreamReader errsr = new InputStreamReader(ferrs);
            //用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            BufferedReader errbr = new BufferedReader(errsr);
            String line = null;
            String lineerr = null;
            //直到读完为止
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            while ((lineerr = errbr.readLine()) != null) {
                sb.append(lineerr + "\n");
            }
            int exitVal = p.waitFor();

            sb.append("exitVal:" + exitVal);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return sb.toString();
    }


    public String getCodeByName(String name) throws Exception {
        EosContractCodeEntity obj = eosContractCodeDAO.findOneByName(name);
        if (obj != null) {
            return obj.getCode();
        }
        return "" +
                "#include <eosiolib/eosio.hpp>\n" +
                "#include <eosiolib/print.hpp>\n" +
                "class ping_contract : public eosio::contract {\n" +
                "    public:\n" +
                "        using eosio::contract::contract;\n" +
                "        void ping(account_name receiver) {\n" +
                "            eosio::print(\"Pong\");\n" +
                "        }\n" +
                "};\n" +
                "\n" +
                "EOSIO_ABI( ping_contract, (ping))";
    }


    public UpdateResult saveToDataBase(String name, String code) throws Exception {
        Map<String, Object> newValues = new HashMap<>();
        newValues.put("code", code);
        newValues.put("name", name);
        return eosContractCodeDAO.saveOrUpdateByName(name, newValues);
    }


    public String getFolder(String name) {
        String folder = base_folder + name;
        return folder;
    }

    public File saveAsFile(String name, String code) throws IOException {

        String folder = getFolder(name);

        String cpp = folder + "/" + name + ".cpp";


        // 创建文件对象
        File fileText = new File(cpp);


        File fileParent = fileText.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }


        // 向文件写入对象写入信息
        FileWriter fileWriter = new FileWriter(fileText);

        // 写文件
        fileWriter.write(code);
        // 关闭
        fileWriter.close();

        return fileText;
    }


    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }


}
