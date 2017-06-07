package com.sh.mxcy.dao.impl;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.dao.inter.CommDao;
import com.sh.mxcy.dao.model.UserInfoModel;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository("CommDaoImpl")
public class CommDaoImpl extends SqlSessionDaoSupport implements CommDao {

	public OutDBJSONObject getUserInfo(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectOne("getUserInfo", inDBJSONObject);
	}

	@Override
	public OutDBJSONObject getMaterialNum(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectOne("getMaterialNum", inDBJSONObject);
	}


}
