package com.sh.mxcy.dao.inter;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Fanjiuyi on 2017/5/18.
 */
@Repository("PartsDao")
public interface PartsDao {

    List<OutDBJSONObject> in_query(InDBJSONObject entity) throws Exception;

    List<OutDBJSONObject> out_query(InDBJSONObject entity) throws Exception;

    int in_query_total(InDBJSONObject entity) throws Exception;

    int out_query_total(InDBJSONObject entity) throws Exception;

    void out(InDBJSONObject entity) throws Exception;

    void out_oper(InDBJSONObject entity) throws Exception;

    void insert(InDBJSONObject entity) throws Exception;

    int delete(InDBJSONObject entity) throws Exception;

    int update(InDBJSONObject entity) throws Exception;

    void audit(InDBJSONObject entity) throws Exception;
}
