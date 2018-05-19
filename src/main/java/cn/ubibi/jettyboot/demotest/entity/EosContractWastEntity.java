package cn.ubibi.jettyboot.demotest.entity;

public class EosContractWastEntity {

    private int id;
    private String contract_name;
    private String code_name;
    private String wast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getWast() {
        return wast;
    }

    public void setWast(String wast) {
        this.wast = wast;
    }
}
