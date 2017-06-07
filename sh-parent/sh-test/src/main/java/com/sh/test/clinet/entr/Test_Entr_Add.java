package com.sh.test.clinet.entr;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Entr_Add {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","entr_add");

        JSONObject busiObject = new JSONObject();
        busiObject.put("name","钢管");
        busiObject.put("figure_no","1534542523");
        busiObject.put("entr_unit","XXXX材料厂");
        busiObject.put("num","5000");
        busiObject.put("type_code","1");
        busiObject.put("pro_plan_id","1");

        busiObject.put("entr_name","张三");
        busiObject.put("start_time","20170520154700");
        busiObject.put("end_time","20170525154700");

        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
