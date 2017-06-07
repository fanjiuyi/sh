package com.sh.mxcy.dao.impl;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.dao.inter.MaterialDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("MaterialDaoImpl")
public class MaterialDaoImpl extends SqlSessionDaoSupport implements MaterialDao {

	@Override
	public List<OutDBJSONObject> query(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.MaterialDaoImpl.query",entity);
	}
	@Override
	public List<OutDBJSONObject> material_oper_que(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectList("com.sh.mxcy.dao.impl.MaterialDaoImpl.material_oper_que",entity);
	}
	@Override
	public int query_total(InDBJSONObject entity) throws Exception {
		return getSqlSession().selectOne("com.sh.mxcy.dao.impl.MaterialDaoImpl.query_total",entity);
	}

	@Override
	public void insert(InDBJSONObject entity) throws Exception {
		getSqlSession().insert("com.sh.mxcy.dao.impl.MaterialDaoImpl.insert",entity);
	}

	@Override
	public int delete(InDBJSONObject entity) throws Exception {
		return getSqlSession().update("com.sh.mxcy.dao.impl.MaterialDaoImpl.delete",entity);
	}

	@Override
	public int update(InDBJSONObject entity) throws Exception {
		return getSqlSession().update("com.sh.mxcy.dao.impl.MaterialDaoImpl.update",entity);
	}

	@Override
	public void audit(InDBJSONObject entity) throws Exception {
		getSqlSession().update("com.sh.mxcy.dao.impl.MaterialDaoImpl.audit",entity);
	}
	public void insert_oper(InDBJSONObject entity) throws Exception {
			getSqlSession().insert("com.sh.mxcy.dao.impl.MaterialDaoImpl.insert_oper",entity);
	}
}
