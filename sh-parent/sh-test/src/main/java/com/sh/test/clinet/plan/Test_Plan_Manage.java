package com.sh.test.clinet.plan;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Plan_Manage {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","plan_manage");

        JSONObject busiObject = new JSONObject();
        busiObject.put("id","24");
        busiObject.put("workshop","1");
        busiObject.put("next_workshop","2");
        busiObject.put("type_code","2");
        busiObject.put("remarks","2000");
        busiObject.put("start_time","2017-06-02 00:00:00");
        busiObject.put("end_time","2017-06-08 00:00:00");
        busiObject.put("entr_unit","蓝莓加工场");
        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_pro_url);
    }
}
