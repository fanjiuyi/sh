package com.sh.utils.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

    static Logger log = LoggerFactory.getLogger(JsonUtils.class);

	private static SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty , SerializerFeature.WriteNullListAsEmpty};

    public static String toJsonString(Object object) {
        try {
            return JSONObject.toJSONString(object,features);
        } catch (Exception e) {
            log.warn("write to json string error:{}" + object, e);
            return null;
        }
    }

    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return JSONObject.parseObject(jsonString,clazz);
        } catch (Exception e) {
            log.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }


//    /**
//     * xml转json
//     * @param xml
//     * @return
//     */
//	public static String xml2JSON(String xml) {
//		return new XMLSerializer().read(xml).toString();
//	}
//
//
//	/**
//	 * json转xml
//	 * @param json
//	 * @return
//	 */
//	public static String json2XML(String json) {
//		String xml = new XMLSerializer().write( net.sf.json.JSONObject.fromObject(json) );
//		return xml;
//	}
	
	
	
}
