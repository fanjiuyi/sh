package com.sh.mxcy.service.comm;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.common.StringUtils;
import com.sh.utils.logger.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.sh.mxcy.dao.inter.CommDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 范玖祎
 * @date 2017/5/16 14:40
 * @description 用户登陆 验证
 */
@Service("Apicomm_loginInvokeImpl")
public class Apicomm_loginInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("CommDaoImpl")
    CommDao CommDaoImpl;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();
        InDBJSONObject inDBJSONObject = new InDBJSONObject();
        OutDBJSONObject outDBJSONObject = new OutDBJSONObject();

        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "loginname", "loginpwd");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "loginname", "loginpwd");

            outDBJSONObject = CommDaoImpl.getUserInfo(inDBJSONObject);

            if(StringUtils.isEmpty(outDBJSONObject)||outDBJSONObject.size()<=0){
                ret.setErrorRet("011008","用户名密码错误!");
                return ret;
            }

            String type = outDBJSONObject.getString("type");
            outDBJSONObject.clear();
            outDBJSONObject.put("type", type);
            ret.setBusiParam(outDBJSONObject);
            ret.setSuccess();

        }
        return ret;
    }

}
