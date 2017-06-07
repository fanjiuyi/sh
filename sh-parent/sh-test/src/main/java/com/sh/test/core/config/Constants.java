package com.sh.test.core.config;


import com.sh.utils.common.PropertiesUtils;
import com.sh.utils.common.StringUtils;

import java.util.Properties;

/**
 * @author Fan JiuYi
 * @version 1.0
 * @date：2016-7-26 下午04:22:09
 * @Description: TODO(全局变量表)
 */
public class Constants {
    public static String sh_mxcy_url = "http://127.0.0.1:8080/mxcy/api/gateway.do";
    public static String sh_mxcy_pro_url = "http://47.93.5.209:8080/mxcy/api/gateway.do";
    public static String TYF_SECRETKEYTYPE_MD5 = "MD5";
    public static String TYF_SECRETKEYTYPE_3DES = "3DES";
    public static String RET_SUCCESS = "000000";
    public static String[] DES3_SECRETKEY_ARR = new String[]{};
    public static String[] MD5_SECRETKEY_ARR = new String[]{};
    public static String HTTPPOST_CONNECTTIMEOUT = "10000";
    public static String HTTPPOST_SOCKETTIMEOUT = "30000";
    public static String HTTPCLIENT_CONN_MANAGER_TIMEOUT = "5000";
    public static String MAX_CONNECTIONS_TOTAL = "200";
    public static String MAX_CONNECTIONS_PER_ROUTE = "200";

}
