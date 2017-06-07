package com.sh.mxcy.dao.impl;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.dao.inter.CommDao;
import com.sh.mxcy.dao.inter.SysparmDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("SysparmDaoImpl")
public class SysparmDaoImpl extends SqlSessionDaoSupport implements SysparmDao {

	public List<OutDBJSONObject> query(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.SysparmImpl.query", inDBJSONObject);
	}

	@Override
	public void update(InDBJSONObject Entity) throws Exception {
		getSqlSession().update("com.sh.mxcy.dao.impl.SysparmImpl.update", Entity);
	}

	@Override
	public void insert(InDBJSONObject Entity) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.SysparmImpl.insert", Entity);
	}

	@Override
	public void delete(InDBJSONObject Entity) throws Exception {
		getSqlSession().update("com.sh.mxcy.dao.impl.SysparmImpl.delete", Entity);
	}

	@Override
	public int query_exit(InDBJSONObject Entity) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.SysparmImpl.query_exit", Entity);
	}


}
