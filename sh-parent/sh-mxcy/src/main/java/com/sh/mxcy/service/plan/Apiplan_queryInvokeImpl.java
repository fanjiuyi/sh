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

import java.util.List;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 生产计划--查询
 */
@Service("Apiplan_queryInvokeImpl")
public class Apiplan_queryInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("PlanDaoImpl")
    PlanDao PlanDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();

        InDBJSONObject inDBJSONObject = new InDBJSONObject();

        List<OutDBJSONObject> outDBJSONObject;
        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "page_index", "page_size");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "page_index", "page_size", "id", "name", "plan_num", "figure_no", "status_code", "figure_no", "cre_time_start", "cre_time_end");
            //处理分页参数
            HandlePubDataUtil.limitParmHandle(inDBJSONObject);
            int total = PlanDao.query_total(inDBJSONObject);
            outDBJSONObject = PlanDao.query(inDBJSONObject);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total", total);
            jsonObject.put("list", outDBJSONObject);
            jsonObject.put("page_index", inDBJSONObject.getString("page_index"));
            ret.setBusiParam(jsonObject);
            ret.setSuccess();

        }
        return ret;
    }

}
