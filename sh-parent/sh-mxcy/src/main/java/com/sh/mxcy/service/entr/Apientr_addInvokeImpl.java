package com.sh.mxcy.service.entr;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.EntrDao;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.logger.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 委外-新增
 */
@Service("Apientr_addInvokeImpl")
public class Apientr_addInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("EntrDaoImpl")
    EntrDao EntrDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();
        InDBJSONObject inDBJSONObject = new InDBJSONObject();
        OutDBJSONObject outDBJSONObject =new OutDBJSONObject();
        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "name", "figure_no", "entr_unit", "num", "type_code", "pro_plan_id", "entr_name", "start_time", "end_time");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "name", "figure_no", "entr_unit", "num", "type_code", "pro_plan_id", "entr_name", "start_time", "end_time", "remarks");

            EntrDao.insert(inDBJSONObject);
            ret.setBusiParam(outDBJSONObject);
            ret.setSuccess();

        }
        return ret;
    }

}
