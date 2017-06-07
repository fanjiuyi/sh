package com.sh.test.clinet.parts;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Parts_Out {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","parts_out");

        JSONObject busiObject = new JSONObject();
        busiObject.put("id","2");
        busiObject.put("num","20000");
        busiObject.put("user","fanjy");
        busiObject.put("use_work","XX车间");

        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
