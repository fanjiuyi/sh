package com.sh.mxcy.dao.inter;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PlanDao")
public interface PlanDao {

	List<OutDBJSONObject> query(InDBJSONObject entity) throws Exception;

	List<OutDBJSONObject> plan_show(InDBJSONObject entity) throws Exception;

	int query_total(InDBJSONObject entity) throws Exception;

	void insert(InDBJSONObject entity) throws Exception;

	int delete(InDBJSONObject entity) throws Exception;

	int update(InDBJSONObject entity) throws Exception;

	void audit(InDBJSONObject entity) throws Exception;

	void oper_insert(InDBJSONObject entity) throws Exception;
}
