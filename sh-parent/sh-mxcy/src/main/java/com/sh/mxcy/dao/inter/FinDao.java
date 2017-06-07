package com.sh.mxcy.dao.inter;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FinDao")
public interface FinDao {

	List<OutDBJSONObject> mp_query(InDBJSONObject Entity) throws Exception;
	List<OutDBJSONObject> fmp_query(InDBJSONObject Entity) throws Exception;
	List<OutDBJSONObject> query(InDBJSONObject Entity) throws Exception;
	int query_total(InDBJSONObject Entity) throws Exception;
	List<OutDBJSONObject> out_oper_query(InDBJSONObject Entity) throws Exception;
	int out_oper_query_total(InDBJSONObject Entity) throws Exception;
	void out(InDBJSONObject Entity) throws Exception;
	void insert(InDBJSONObject Entity) throws Exception;
	void out_insert_oper(InDBJSONObject inDBJSONObject) throws Exception;
	void insert_oper(InDBJSONObject inDBJSONObject) throws Exception;
}
