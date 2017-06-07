package com.sh.test.clinet.entr;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Entr_Audit {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","entr_audit");

        JSONObject busiObject = new JSONObject();
        busiObject.put("id","1");
        busiObject.put("status_code","1");

        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
