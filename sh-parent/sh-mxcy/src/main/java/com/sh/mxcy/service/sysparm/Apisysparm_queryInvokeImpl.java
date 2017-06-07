package com.sh.mxcy.service.sysparm;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.MaterialDao;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.logger.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.sh.mxcy.dao.inter.SysparmDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 系统参数-查询
 */
@Service("Apisysparm_queryInvokeImpl")
public class Apisysparm_queryInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("SysparmDaoImpl")
    SysparmDao SysparmDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();

        InDBJSONObject inDBJSONObject = new InDBJSONObject();

        List<OutDBJSONObject> outDBJSONObject;

        OutDBJSONObject outJSONObject = new OutDBJSONObject();

        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "parent_id");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "parent_id");
            String[] parents = inDBJSONObject.getString("parent_id").split(",");
            for(String parent : parents){
                inDBJSONObject.put("parent_id",parent);
                outDBJSONObject = SysparmDao.query(inDBJSONObject);
                outJSONObject.put(parent,outDBJSONObject);
            }

            outDBJSONObject = SysparmDao.query(inDBJSONObject);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list", outJSONObject);
            ret.setBusiParam(jsonObject);
            ret.setSuccess();

        }
        return ret;
    }

}
