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
 * @description 成品仓库-出入库记录查询
 */
@Service("Apifin_out_oper_queryInvokeImpl")
public class Apifin_out_oper_queryInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("FinDaoImpl")
    FinDao FinDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();
        InDBJSONObject inDBJSONObject = new InDBJSONObject();
        //业务数据校验
//        ret = HandlePubDataUtil.checkInData(inParamJO, "cre_time_start", "cre_time_end");
//        ret = HandlePubDataUtil.checkInData(inParamJO, "cre_time_start", "cre_time_end");
        ret.setSuccess();
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "page_index", "page_size", "plan_num", "figure_no", "cre_time_start", "cre_time_end", "oper_type");
            //处理分页参数
//            HandlePubDataUtil.limitParmHandle(inDBJSONObject);
//            int total = FinDao.out_oper_query_total(inDBJSONObject);
            List<OutDBJSONObject> outDBJSONObject = FinDao.out_oper_query(inDBJSONObject);
            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("total", total);
            jsonObject.put("list", outDBJSONObject);
//            jsonObject.put("page_index", inDBJSONObject.getString("page_index"));
            ret.setBusiParam(jsonObject);
            ret.setSuccess();

        }
        return ret;
    }

}
