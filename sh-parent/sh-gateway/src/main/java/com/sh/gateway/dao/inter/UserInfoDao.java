package com.sh.gateway.dao.inter;

import com.sh.gateway.dao.model.UserInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserInfoDao")
public interface UserInfoDao {
	
	  UserInfoModel getUserInfo(UserInfoModel UserInfoEntity) throws Exception;
	    
	  UserInfoModel getUserInfoById(String userid) throws Exception;
      
	  List<UserInfoModel> getUserInfoAll() throws Exception;
      
	  void insertUserInfo(UserInfoModel entity) throws Exception;
      
	  int deleteUserInfo(UserInfoModel entity) throws Exception;
      
	  int updateUserInfo(UserInfoModel entity) throws Exception;
}
