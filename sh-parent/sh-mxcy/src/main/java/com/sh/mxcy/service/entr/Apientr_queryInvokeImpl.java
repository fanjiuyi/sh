package com.sh.mxcy.service.entr;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.EntrDao;
import com.sh.mxcy.dao.inter.MaterialDao;
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
 * @description 委外-查询
 */
@Service("Apientr_queryInvokeImpl")
public class Apientr_queryInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("EntrDaoImpl")
    EntrDao EntrDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();
        InDBJSONObject inDBJSONObject = new InDBJSONObject();
        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "page_index", "page_size");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "page_index", "page_size", "id", "name", "pfigure_no", "entr_unit", "type_code", "status_code", "cre_time_start", "cre_time_end");
            //处理分页参数
            HandlePubDataUtil.limitParmHandle(inDBJSONObject);
            int total = EntrDao.query_total(inDBJSONObject);
            List<OutDBJSONObject> outDBJSONObject  = EntrDao.query(inDBJSONObject);
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
