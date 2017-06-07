package com.sh.mxcy.dao.impl;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.dao.inter.EntrDao;
import com.sh.mxcy.dao.inter.MaterialDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("EntrDaoImpl")
public class EntrDaoImpl extends SqlSessionDaoSupport implements EntrDao {

	@Override
	public List<OutDBJSONObject> query(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.EntrDaoImpl.query",entity);
	}

	@Override
	public int query_total(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.EntrDaoImpl.query_total",entity);
	}

	@Override
	public int query_wc_total(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.EntrDaoImpl.query_wc_total",entity);
	}

	@Override
	public void insert(InDBJSONObject entity) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.EntrDaoImpl.insert",entity);
	}
	@Override
	public void plan_insert(InDBJSONObject entity) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.EntrDaoImpl.plan_insert",entity);
	}

	@Override
	public int delete(InDBJSONObject entity) throws Exception {
		return getSqlSession().update("com.sh.mxcy.dao.impl.EntrDaoImpl.delete",entity);
	}

	@Override
	public int update(InDBJSONObject entity) throws Exception {
		return getSqlSession().update("com.sh.mxcy.dao.impl.EntrDaoImpl.update",entity);
	}

	@Override
	public void audit(InDBJSONObject entity) throws Exception {
		getSqlSession().update("com.sh.mxcy.dao.impl.EntrDaoImpl.audit",entity);
	}
}
