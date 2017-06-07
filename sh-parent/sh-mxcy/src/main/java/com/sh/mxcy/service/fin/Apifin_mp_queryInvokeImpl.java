package com.sh.mxcy.service.fin;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.FinDao;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.logger.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 半成品毛坯-查询
 */
@Service("Apifin_mp_queryInvokeImpl")
public class Apifin_mp_queryInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("FinDaoImpl")
    FinDao FinDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();
        InDBJSONObject inDBJSONObject = new InDBJSONObject();
        List<OutDBJSONObject> outDBJSONObject = FinDao.mp_query(inDBJSONObject);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", outDBJSONObject);
        ret.setBusiParam(jsonObject);
        ret.setSuccess();

        return ret;
    }

}
