package com.sh.mxcy.dao.impl;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.dao.inter.EntrDao;
import com.sh.mxcy.dao.inter.PlanDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("PlanDaoImpl")
public class PlanDaoImpl extends SqlSessionDaoSupport implements PlanDao {


	@Override
	public List<OutDBJSONObject> query(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.PlanDaoImpl.query",inDBJSONObject);
	}
	@Override
	public List<OutDBJSONObject> plan_show(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.PlanDaoImpl.plan_show",inDBJSONObject);
	}

	@Override
	public int query_total(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.PlanDaoImpl.query_total",inDBJSONObject);
	}

	@Override
	public void insert(InDBJSONObject inDBJSONObject) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.PlanDaoImpl.insert",inDBJSONObject);
	}

	@Override
	public int delete(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().update("com.sh.mxcy.dao.impl.PlanDaoImpl.delete",inDBJSONObject);
	}

	@Override
	public int update(InDBJSONObject inDBJSONObject) throws Exception {
		return getSqlSession().update("com.sh.mxcy.dao.impl.PlanDaoImpl.update",inDBJSONObject);
	}

	@Override
	public void audit(InDBJSONObject inDBJSONObject) throws Exception {
		getSqlSession().update("com.sh.mxcy.dao.impl.PlanDaoImpl.audit",inDBJSONObject);
	}
	@Override
	public void oper_insert(InDBJSONObject inDBJSONObject) throws Exception {
			getSqlSession().insert("com.sh.mxcy.dao.impl.PlanDaoImpl.insert_oper",inDBJSONObject);
	}
}
