package com.sh.mxcy.service.sysparm;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.SysparmDao;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 系统参数-删除
 */
@Service("Apisysparm_delInvokeImpl")
public class Apisysparm_delInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("SysparmDaoImpl")
    SysparmDao SysparmDao;

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

            SysparmDao.delete(inDBJSONObject);

            ret.setSuccess();

        }
        return ret;
    }

}
