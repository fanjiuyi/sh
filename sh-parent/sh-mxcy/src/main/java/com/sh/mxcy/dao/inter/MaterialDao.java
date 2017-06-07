package com.sh.mxcy.dao.inter;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Fanjiuyi on 2017/5/18.
 */
@Repository("MaterialDao")
public interface MaterialDao {

    List<OutDBJSONObject> query(InDBJSONObject entity) throws Exception;

    List<OutDBJSONObject> material_oper_que(InDBJSONObject entity) throws Exception;

    int query_total(InDBJSONObject entity) throws Exception;

    void insert(InDBJSONObject entity) throws Exception;

    int delete(InDBJSONObject entity) throws Exception;

    int update(InDBJSONObject entity) throws Exception;

    void audit(InDBJSONObject entity) throws Exception;

    void insert_oper(InDBJSONObject entity) throws Exception;
}
