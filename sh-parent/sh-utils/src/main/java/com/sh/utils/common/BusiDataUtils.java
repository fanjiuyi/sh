package com.sh.utils.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sh.utils.config.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Fanjiuyi on 2017/1/5.
 */
public class BusiDataUtils {

    public static Properties getInit_properties() {
        Properties init_properties;

        init_properties = (Properties) Constants.proMap.get("init.properties");

        if (StringUtils.isEmpty(init_properties)) {
            init_properties = PropertiesUtils.getProperties(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("init.properties"));
            Constants.proMap.put("init.properties", init_properties);
        }

        return init_properties;
    }

    public static String getInitPro(String key) {
        String value;
        value = (String) Constants.proMap.get(key);
        if (StringUtils.isEmpty(value)) {
            Object val = getInit_properties().get(key);
            if(StringUtils.isNotEmpty(val)){
                value = val.toString();
            }
            if (!StringUtils.isEmpty(value))
                Constants.proMap.put(key, value);

        }
        return value;
    }
}
