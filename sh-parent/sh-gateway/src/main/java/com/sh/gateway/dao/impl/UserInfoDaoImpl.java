package com.sh.gateway.dao.impl;

import com.sh.gateway.dao.inter.UserInfoDao;
import com.sh.gateway.dao.model.UserInfoModel;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("UserInfoDaoImpl")
public class UserInfoDaoImpl extends SqlSessionDaoSupport implements UserInfoDao {

	public int deleteUserInfo(UserInfoModel entity) {
		return 0;
	}
	@Transactional(rollbackFor=Exception.class)
	public UserInfoModel getUserInfo(UserInfoModel UserInfoModel) throws Exception {
		 UserInfoModel =  getSqlSession().selectOne("getUserInfo", UserInfoModel);
		return UserInfoModel;
	}

    @Override
    public UserInfoModel getUserInfoById(String userid) throws Exception {
        return null;
    }

    public List<UserInfoModel> getUserInfoAll() {
		return getSqlSession().selectList("getUserInfoAll");
	}

	public UserInfoModel getUserInfoAndClass(String userid) {
		return null;
	}

	public void insertUserInfo(UserInfoModel entity) {
	}

	public int updateUserInfo(UserInfoModel entity) {
		return 0;
	}

}
