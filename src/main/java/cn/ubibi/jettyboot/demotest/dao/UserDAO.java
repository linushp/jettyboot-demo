package cn.ubibi.jettyboot.demotest.dao;

import cn.ubibi.jettyboot.demotest.dao.base.MyDAO;
import cn.ubibi.jettyboot.demotest.entity.UserEntity;
import cn.ubibi.jettyboot.framework.commons.StringUtils;
import cn.ubibi.jettyboot.framework.commons.model.Page;
import cn.ubibi.jettyboot.framework.rest.annotation.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserDAO extends MyDAO<UserEntity> {

    public UserDAO() {
        super(UserEntity.class, "m_monster_item");
    }


    public List<UserEntity> findByName(String username) throws Exception {
        return findByWhere("where name = ?", username);
    }


    public List<UserEntity> findByNameLike(String username) throws Exception {
        return findByWhere("where name like concat('%',?,'%')", username);
    }


    public List<UserEntity> findByNameAndSex(String username, int sex) throws Exception {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", username);
        condition.put("mid", sex);
        return findByWhere(toWhereSqlAndArgs(condition));
    }


    public List<UserEntity> findByNameLikeAndSex(String username, int sex) throws Exception {
        return findByWhere("where name like concat('%',?,'%') and mid = ?", username, sex);
    }


    public Page<UserEntity> findPageByName(int pageNo, int pageSize, String name) throws Exception {
        return findPage(pageNo, pageSize, "where name = ?", "order by id desc", name);
    }

    public  List<UserEntity> findByIdIn(List<String> idList) throws Exception {
        String idListStr = StringUtils.join(idList,",");
        return findByWhere("where id in (?)", idListStr);
    }


}
