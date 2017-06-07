package com.sh.mxcy.dao.inter;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.dao.model.UserInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CommDao")
public interface CommDao {

	OutDBJSONObject getUserInfo(InDBJSONObject Entity) throws Exception;
	OutDBJSONObject getMaterialNum(InDBJSONObject Entity) throws Exception;

}
