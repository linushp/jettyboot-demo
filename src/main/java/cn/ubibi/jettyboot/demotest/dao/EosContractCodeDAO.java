package cn.ubibi.jettyboot.demotest.dao;

import cn.ubibi.jettyboot.demotest.dao.base.MyDAO;
import cn.ubibi.jettyboot.demotest.entity.EosContractCodeEntity;
import cn.ubibi.jettyboot.framework.jdbc.model.UpdateResult;
import cn.ubibi.jettyboot.framework.rest.annotation.Service;

import java.util.Map;

@Service
public class EosContractCodeDAO  extends MyDAO<EosContractCodeEntity> {
    public EosContractCodeDAO() {
        super(EosContractCodeEntity.class, "eos_contract_code");
    }

    public UpdateResult saveOrUpdateByName(String name, Map<String, Object> newValues) throws Exception {
        return this.saveOrUpdate(newValues,"where name = ?",name);
    }

    public EosContractCodeEntity findOneByName(String name) throws Exception {
        return this.findOneByWhere("where name = ?",name);
    }
}
