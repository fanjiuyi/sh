package com.sh.mxcy.service.parts;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.logger.LogHelper;
import com.sh.mxcy.dao.inter.PartsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 配件-新增
 */
@Service("Apiparts_addInvokeImpl")
public class Apiparts_addInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("PartsDaoImpl")
    PartsDao PartsDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();
        InDBJSONObject inDBJSONObject = new InDBJSONObject();
        OutDBJSONObject outDBJSONObject = new OutDBJSONObject();
        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "name", "type_code", "spec", "mea_unit", "pro_num", "type_code");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "name", "type_code", "spec", "mea_unit", "pro_num", "type_code", "remarks");

            PartsDao.insert(inDBJSONObject);
            ret.setBusiParam(outDBJSONObject);
            ret.setSuccess();

        }
        return ret;
    }

}
