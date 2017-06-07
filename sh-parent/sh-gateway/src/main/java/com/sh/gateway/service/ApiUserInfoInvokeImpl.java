package com.sh.gateway.service;

import com.alibaba.fastjson.JSONObject;
import com.sh.gateway.core.model.RetJSONObject;
import com.sh.gateway.dao.model.UserInfoModel;
import com.sh.gateway.service.base.ServiceHandlerInvoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.sh.gateway.dao.inter.UserInfoDao;
import com.sh.gateway.core.utils.web.SendSmsUtils;
import java.util.List;

/**
 * Created by Fanjiuyi on 2017/4/11.
 */
@Service("ApiUserInfoInvokeImpl")
public class ApiUserInfoInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("UserInfoDaoImpl")
    UserInfoDao UserInfoDao;

    @Autowired
    @Qualifier("SendSmsUtils")
    SendSmsUtils SendSmsUtils;

    @Override
    public RetJSONObject handler(JSONObject inParamJO) {
        RetJSONObject retJSONObject = new RetJSONObject();
        UserInfoModel userInfoModel = new UserInfoModel();
//        userInfoModel.set
        try {
            List<UserInfoModel> list = UserInfoDao.getUserInfoAll();
            JSONObject JsObj = new JSONObject();
            JsObj.put("list",list);
            retJSONObject.setBusiParam(JsObj);
            retJSONObject.setSuccess();
//            String resStr = SendSmsUtils.send("13115711391","666888");
//            LogHelper.info("短信发送结果:"+resStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retJSONObject;
    }

}
