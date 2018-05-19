package cn.ubibi.jettyboot.demotest.dao;


import cn.ubibi.jettyboot.demotest.dao.base.MyDAO;
import cn.ubibi.jettyboot.demotest.entity.EosContractWastEntity;
import cn.ubibi.jettyboot.framework.rest.annotation.Service;


@Service
public class EosContractWastDAO extends MyDAO<EosContractWastEntity> {
    public EosContractWastDAO() {
        super(EosContractWastEntity.class, "eos_contract_wast");
    }
}