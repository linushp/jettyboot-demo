package cn.ubibi.jettyboot.demotest.entity;


/**
 * create table eos_contract_abi
 * (
 * id int null,
 * contract_name varchar(200) null,
 * code_name varchar(200) null,
 * abi longtext null
 * )
 * engine=InnoDB;
 */
public class EosContractAbiEntity {

    private int id;
    private String contract_name;
    private String code_name;
    private String abi;


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

    public String getAbi() {
        return abi;
    }

    public void setAbi(String abi) {
        this.abi = abi;
    }
}
