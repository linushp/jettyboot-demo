package cn.ubibi.jettyboot.demotest.dao;


import cn.ubibi.jettyboot.demotest.dao.base.MyDAO;
import cn.ubibi.jettyboot.demotest.entity.EosContractAbiEntity;
import cn.ubibi.jettyboot.framework.rest.annotation.Service;


@Service
public class EosContractAbiDAO extends MyDAO<EosContractAbiEntity>{
    public EosContractAbiDAO() {
        super(EosContractAbiEntity.class, "eos_contract_abi");
    }
}
