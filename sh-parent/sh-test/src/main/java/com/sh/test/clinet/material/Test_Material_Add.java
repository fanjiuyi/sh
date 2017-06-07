package com.sh.test.clinet.material;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.http.HttpUtils;

/**
 * Created by Fanjiuyi on 2017/5/20.
 */
public class Test_Material_Add {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("api_model","pro");
        jsonObject.put("action_name","material_add");

        JSONObject busiObject = new JSONObject();
        busiObject.put("name","钢管");
        busiObject.put("spec","15cm");
        busiObject.put("pro_unit","XXXX材料厂");
        busiObject.put("mea_unit","根");
        busiObject.put("pro_num","5000");
        busiObject.put("type_code","2");
        busiObject.put("name","钢管");

        jsonObject.put("busi_param",busiObject.toJSONString());
        HttpUtils.sendMsg(jsonObject, Constants.sh_mxcy_url);
    }
}
