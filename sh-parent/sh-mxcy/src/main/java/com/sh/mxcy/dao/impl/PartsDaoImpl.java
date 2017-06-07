package com.sh.mxcy.dao.impl;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.dao.inter.PartsDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("PartsDaoImpl")
public class PartsDaoImpl extends SqlSessionDaoSupport implements PartsDao {


	@Override
	public List<OutDBJSONObject> in_query(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.PartsDaoImpl.in_query",entity);
	}

	@Override
	public List<OutDBJSONObject> out_query(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.PartsDaoImpl.out_query",entity);
	}

	@Override
	public int in_query_total(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.PartsDaoImpl.in_query_total",entity);
	}

	@Override
	public int out_query_total(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.PartsDaoImpl.out_query_total",entity);
	}

	@Override
	public void out(InDBJSONObject entity) throws Exception {
		getSqlSession().update("com.sh.mxcy.dao.impl.PartsDaoImpl.out",entity);

	}
	public void out_oper(InDBJSONObject entity) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.PartsDaoImpl.out_oper",entity);

	}

	@Override
	public void insert(InDBJSONObject entity) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.PartsDaoImpl.insert",entity);
	}

	@Override
	public int delete(InDBJSONObject entity) throws Exception {
		return getSqlSession().update("com.sh.mxcy.dao.impl.PartsDaoImpl.delete",entity);
	}

	@Override
	public int update(InDBJSONObject entity) throws Exception {
		return getSqlSession().update("com.sh.mxcy.dao.impl.PartsDaoImpl.update",entity);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void audit(InDBJSONObject entity) throws Exception {
		getSqlSession().update("com.sh.mxcy.dao.impl.PartsDaoImpl.audit",entity);
		if("1".equals(entity.getString("status_code"))){
			getSqlSession().insert("com.sh.mxcy.dao.impl.PartsDaoImpl.insert_oper",entity);
		}
	}
}
