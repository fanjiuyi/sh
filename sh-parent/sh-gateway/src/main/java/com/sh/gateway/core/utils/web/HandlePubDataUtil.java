package com.sh.gateway.core.utils.web;

import com.alibaba.fastjson.JSONObject;
import com.sh.gateway.core.model.RetJSONObject;
import com.sh.utils.common.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


public class HandlePubDataUtil {

    static List<String> paramNameList = Arrays.asList(new String[]{"api_model", "action_name", "busi_param"});

    static List<String> checkParamList = Arrays.asList(new String[]{"api_model", "action_name", "busi_param"});

    public static JSONObject resolveReqData(HttpServletRequest req) {
        JSONObject p = new JSONObject();
        for (String paramName : paramNameList) {
            String v = req.getParameter(paramName);
            if (StringUtils.isNotEmpty(v)) {
                p.put(paramName, v);
            }
        }
        return p;
    }

    public static RetJSONObject checkData(JSONObject p) {
        RetJSONObject ret = new RetJSONObject();
        //没有错误
        boolean notError = true;

            // 解析头
            for (String key : checkParamList) {
                if (!p.containsKey(key)) {
                    //this error
                    notError = false;
                    ret.setErrorRet("011001", "[" + key + "] 不能为空.");
                }
            }
        if (notError) {
            ret.setSuccess();
        }
        return ret;
    }



}
