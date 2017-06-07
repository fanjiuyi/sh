package com.sh.mxcy.service.parts;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.MaterialDao;
import com.sh.mxcy.dao.inter.PartsDao;
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
 * @description 配件-入库查询
 */
@Service("Apiparts_in_queryInvokeImpl")
public class Apiparts_in_queryInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("PartsDaoImpl")
    PartsDao PartsDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();

        InDBJSONObject inDBJSONObject = new InDBJSONObject();

        List<OutDBJSONObject> outDBJSONObject;

        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "page_index", "page_size");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "page_index", "page_size", "id", "name", "type_code", "status_code", "spec", "cre_time_start", "cre_time_end");
            //处理分页参数
            HandlePubDataUtil.limitParmHandle(inDBJSONObject);
            int total = PartsDao.in_query_total(inDBJSONObject);
            outDBJSONObject = PartsDao.in_query(inDBJSONObject);
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
