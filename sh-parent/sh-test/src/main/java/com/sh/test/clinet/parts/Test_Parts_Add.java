package com.sh.test.clinet.parts;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Parts_Add {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","parts_add");

        JSONObject busiObject = new JSONObject();
        busiObject.put("name","钢管");
        busiObject.put("spec","15cm");
        busiObject.put("mea_unit","件");
        busiObject.put("pro_num","5000");
        busiObject.put("type_code","1");
        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
