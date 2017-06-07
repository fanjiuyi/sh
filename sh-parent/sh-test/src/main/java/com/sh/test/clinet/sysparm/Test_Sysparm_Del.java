package com.sh.test.clinet.sysparm;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Sysparm_Del {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","sysparm_del");

        JSONObject busiObject = new JSONObject();
        busiObject.put("id","25");

        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
