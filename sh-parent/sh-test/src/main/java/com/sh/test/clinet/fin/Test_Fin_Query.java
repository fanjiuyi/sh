package com.sh.test.clinet.fin;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Fin_Query {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","fin_query");

        JSONObject busiObject = new JSONObject();
        busiObject.put("page_index","1");
        busiObject.put("page_size","10");

        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
