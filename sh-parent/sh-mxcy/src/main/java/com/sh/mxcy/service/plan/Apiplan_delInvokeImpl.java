package com.sh.mxcy.service.plan;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.MaterialDao;
import com.sh.mxcy.dao.inter.PlanDao;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.logger.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 生产计划--删除
 */
@Service("Apiplan_delInvokeImpl")
public class Apiplan_delInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("PlanDaoImpl")
    PlanDao PlanDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();

        InDBJSONObject inDBJSONObject = new InDBJSONObject();

        OutDBJSONObject outDBJSONObject = new OutDBJSONObject();

        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "id");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "id");

            PlanDao.delete(inDBJSONObject);
            ret.setBusiParam(outDBJSONObject);
            ret.setSuccess();

        }
        return ret;
    }

}