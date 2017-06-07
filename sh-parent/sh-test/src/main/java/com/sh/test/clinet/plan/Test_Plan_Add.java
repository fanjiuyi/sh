package com.sh.test.clinet.plan;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Plan_Add {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","plan_add");

        JSONObject busiObject = new JSONObject();
        busiObject.put("name","钢管加工");
        busiObject.put("plan_num","123456789");
        busiObject.put("figure_no","123456789");
        busiObject.put("order_num","10000");
        busiObject.put("week_num","2000");
        busiObject.put("day_num","500");
        busiObject.put("start_time","20170521170000");
        busiObject.put("end_time","20170528170000");
        busiObject.put("type_code","2");
        busiObject.put("remarks","XXX...");

        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
