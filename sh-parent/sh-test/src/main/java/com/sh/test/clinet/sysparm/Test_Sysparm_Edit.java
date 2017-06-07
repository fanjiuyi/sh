package com.sh.test.clinet.sysparm;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Sysparm_Edit {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","sysparm_edit");

        JSONObject busiObject = new JSONObject();
        busiObject.put("id","26");
        busiObject.put("name","撞机车间5");
        busiObject.put("val","5");
        busiObject.put("remarks","5");
        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
