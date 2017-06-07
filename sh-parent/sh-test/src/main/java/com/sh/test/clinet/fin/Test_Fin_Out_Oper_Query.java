package com.sh.test.clinet.fin;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Fin_Out_Oper_Query {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","fin_out_oper_query");

        JSONObject busiObject = new JSONObject();
//        busiObject.put("oper_type","1");
//        busiObject.put("cre_time_start","2017-05-31");
////        busiObject.put("cre_time_end","2017-06-02");
        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
