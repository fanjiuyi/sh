package com.sh.mxcy.service.material;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.MaterialDao;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 原材料-出入库记录查询
 */
@Service("Apimaterial_oper_queInvokeImpl")
public class Apimaterial_oper_queInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("MaterialDaoImpl")
    MaterialDao MaterialDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();
        InDBJSONObject inDBJSONObject = new InDBJSONObject();
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "cre_time_start", "cre_time_end","oper_type");
            //处理分页参数
            List<OutDBJSONObject> outDBJSONObject = MaterialDao.material_oper_que(inDBJSONObject);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list", outDBJSONObject);
            ret.setBusiParam(jsonObject);
            ret.setSuccess();

        return ret;
    }

}
