package com.sh.mxcy.dao.impl;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.dao.inter.FinDao;
import com.sh.mxcy.dao.inter.SysparmDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("FinDaoImpl")
public class FinDaoImpl extends SqlSessionDaoSupport implements FinDao {

	@Override
	public List<OutDBJSONObject> mp_query(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.FinDaoImpl.mp_query", inDBJSONObject);
	}

	@Override
	public List<OutDBJSONObject> fmp_query(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.FinDaoImpl.fmp_query", inDBJSONObject);
	}

	@Override
	public List<OutDBJSONObject> out_oper_query(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.FinDaoImpl.out_oper_query", inDBJSONObject);
	}

	@Override
	public int out_oper_query_total(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.FinDaoImpl.out_oper_query_total", inDBJSONObject);
	}

	public List<OutDBJSONObject> query(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.FinDaoImpl.query", inDBJSONObject);
	}

	@Override
	public int query_total(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.FinDaoImpl.query_total", inDBJSONObject);
	}
	public void insert(InDBJSONObject inDBJSONObject) throws Exception {
		  getSqlSession().insert("com.sh.mxcy.dao.impl.FinDaoImpl.insert", inDBJSONObject);
	}
	@Override
	public void out(InDBJSONObject inDBJSONObject) throws Exception {
		getSqlSession().update("com.sh.mxcy.dao.impl.FinDaoImpl.out_update",inDBJSONObject);

	}
	public void out_insert_oper(InDBJSONObject inDBJSONObject) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.FinDaoImpl.out_insert_oper",inDBJSONObject);

	}
	public void insert_oper(InDBJSONObject inDBJSONObject) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.FinDaoImpl.insert_oper",inDBJSONObject);

	}
}
