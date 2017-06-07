package com.sh.mxcy.dao.inter;

import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SysparmDao")
public interface SysparmDao {

    List<OutDBJSONObject> query(InDBJSONObject Entity) throws Exception;

    void update(InDBJSONObject Entity) throws Exception;

    void insert(InDBJSONObject Entity) throws Exception;

    void delete(InDBJSONObject Entity) throws Exception;

    int query_exit(InDBJSONObject Entity) throws Exception;
}
