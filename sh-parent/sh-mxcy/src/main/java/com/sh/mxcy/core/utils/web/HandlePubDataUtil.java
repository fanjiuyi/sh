package com.sh.mxcy.core.utils.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.utils.common.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;


public class HandlePubDataUtil {

    static String[] paramNameList = new String[]{"api_model", "action_name", "busi_param"};

    static String[] checkParamList = new String[]{"api_model", "action_name", "busi_param"};

    static String[] numberParamList = new String[]{"page_size", "page_index", "pro_num","num","order_num","week_num","day_num"};

    static Map numberParamMap = new HashMap(){
        {
            put("page_size","page_size");
            put("page_index","page_index");
            put("pro_num","pro_num");
            put("num","num");
            put("order_num","order_num");
            put("week_num","week_num");
            put("day_num","day_num");
        }
    };


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

    /**
     * @author 范玖祎
     * @date 2017/5/16 14:31
     * @description 接口公共参数 必填参数校验
     */
    public static RetJSONObject checkData(JSONObject p) {
        return checkInData(p, checkParamList);
    }

    /**
     * @author 范玖祎
     * @date 2017/5/16 14:30
     * @description 接口业务参数 必填项校验
     */
    public static RetJSONObject checkInData(JSONObject p, String... checkStr) {
        RetJSONObject ret = new RetJSONObject();
        //没有错误
        boolean notError = true;

        //判断公共数据类型是否正确
        for(String numStr :numberParamList){
            if (p.containsKey(numStr)) {
                if (!isNumeric(p.getString(numStr))) {
                    notError = false;
                    ret.setErrorRet("011001", "[" + numStr + ": "+p.getString(numStr)+"] 必须是数字.");
                }
            }
        }
        if (StringUtils.isEmpty(checkStr)) {
            ret.setSuccess();
            return ret;
        }
        // 判断业务数据是否为空
        for (String key : checkStr) {
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

    public static void main(String[] args) {
        String numStr = "123";
        System.out.println(isNumeric(numStr));
    }
    /**
     * @author 范玖祎
     * @date 2017/5/19 14:05
     * @description 字符串是否为数字格式校验
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * @author 范玖祎
     * @date 2017/5/16 14:35
     * @description 参数复制
     */
    public static void paramCopy(JSONObject inParamJO, InDBJSONObject inDBJSONObject, String... copyStr) {
        if (StringUtils.isEmpty(copyStr)) {
            return;
        }
        for (String key : copyStr) {
            if (inParamJO.containsKey(key)) {
                if(StringUtils.isNotEmpty(inParamJO.get(key))){
                    inDBJSONObject.put(key, inParamJO.get(key));
                }

            }
        }
    }

    /**
     * @author 范玖祎
     * @date 2017/5/16 16:25
     * @description 数据合并
     */
    public static void paramSum(JSONObject inParamJO) {
        JSONObject busiJSONObject = JSON.parseObject(inParamJO.getString("busi_param"));
        Iterator iter = busiJSONObject.keySet().iterator();
        while (iter.hasNext()) {
            String key = String.valueOf(iter.next());
            if(StringUtils.isNotEmpty(busiJSONObject.getString(key)))
            inParamJO.put(key.toLowerCase(), busiJSONObject.getString(key).trim());
        }
    }

    /**
     * @author 范玖祎
     * @date 2017/5/19 13:49
     * @description 前端到DAO 的分页参数转换
     */
    public static void limitParmHandle(InDBJSONObject inDBJSONObject)  throws Exception {
        String page_index = inDBJSONObject.getString("page_index");
        String page_size = inDBJSONObject.getString("page_size");
        Integer limit_start = (Integer.parseInt(page_index)-1) * Integer.parseInt(page_size);
//        Integer limit_end = Integer.parseInt(page_index)* Integer.parseInt(page_size);
        Integer limit_end = Integer.parseInt(page_size);
        inDBJSONObject.put("limit_start",limit_start);
        inDBJSONObject.put("limit_end",limit_end);
    }
}
