package cn.ubibi.jettyboot.demotest.dao;

import cn.ubibi.jettyboot.demotest.dao.base.MyDAO;
import cn.ubibi.jettyboot.demotest.entity.UserEntity;
import cn.ubibi.jettyboot.framework.commons.StringUtils;
import cn.ubibi.jettyboot.framework.commons.model.Page;
import cn.ubibi.jettyboot.framework.commons.xmlstring.XmlString;
import cn.ubibi.jettyboot.framework.jdbc.model.SqlNdArgs;
import cn.ubibi.jettyboot.framework.rest.annotation.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserDAO extends MyDAO<UserEntity> {

    private XmlString xmlString;

    public UserDAO() throws Exception {
        super(UserEntity.class, "m_monster_item");
        this.xmlString = new XmlString(this);
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
        return findByExample(condition);
    }


    public List<UserEntity> findByNameLikeAndSex(String username, int sex) throws Exception {
        return findByWhere("where name like concat('%',?,'%') and mid = ?", username, sex);
    }


    public Page<UserEntity> findPageByName(int pageNo, int pageSize, String name) throws Exception {
        return findPage(pageNo, pageSize, "where name = ?", "order by id desc", name);
    }

    public List<UserEntity> findByIdIn(List<String> idList) throws Exception {
        String idListStr = StringUtils.join(idList, ",");
        return findByWhere("where id in (?)", idListStr);
    }


    public List<UserEntity> findByUsername(String username) throws Exception {

        Map<String, Object> map = new HashMap<>();

        map.put("username", username);
        map.put("schemaTableName", schemaTableName());

        map.put("_conditions","id = #{username}");

        return dataAccess.query(clazz, xmlString.getStringById("findByUsername2"), map);
    }


}
