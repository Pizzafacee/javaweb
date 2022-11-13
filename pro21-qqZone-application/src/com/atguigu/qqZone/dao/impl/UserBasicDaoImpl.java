package com.atguigu.qqZone.dao.impl;

import com.atguigu.myssm.baseDao.BaseDAO;
import com.atguigu.qqZone.dao.UserBasicDao;
import com.atguigu.qqZone.pojo.UserBasic;

import java.util.List;

public class UserBasicDaoImpl extends BaseDAO<UserBasic> implements UserBasicDao {
    @Override
    public UserBasic getUerBasic(String loginId, String pwd) {
        String sql = "select id,loginId,nickName,pwd,headImg from t_user_basic where loginId = ? and pwd = ?";
        UserBasic userBasic = super.load(sql, loginId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "SELECT fid as 'id' FROM t_friend WHERE uid = ?";
        return super.executeQuery(sql, userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        String sql = "select id,loginId,nickName,pwd,headImg from t_user_basic where id = ?";
        UserBasic load = super.load(sql, id);
        return load;
    }
}
