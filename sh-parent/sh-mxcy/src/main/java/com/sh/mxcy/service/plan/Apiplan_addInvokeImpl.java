package com.sh.mxcy.service.plan;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
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
 * @description 生产计划-新增
 */
@Service("Apiplan_addInvokeImpl")
public class Apiplan_addInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("PlanDaoImpl")
    PlanDao PlanDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();

        InDBJSONObject inDBJSONObject = new InDBJSONObject();

        OutDBJSONObject outDBJSONObject = new OutDBJSONObject();

        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "name", "plan_num", "figure_no", "order_num", "start_time", "end_time", "type_code","me_type_code");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "name", "plan_num", "figure_no", "order_num", "start_time", "end_time", "type_code","me_type_code", "week_num", "day_num", "remarks");

            PlanDao.insert(inDBJSONObject);
            ret.setBusiParam(outDBJSONObject);
            ret.setSuccess();

        }
        return ret;
    }

}
