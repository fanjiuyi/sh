package com.sh.utils.common;

import com.alibaba.fastjson.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类描述：
 * 
 * @author: Administrator
 * @version $Id: longshine-codetemplates.xml,v 1.1 2012/09/05 04:45:47
 *          administrator Exp $
 * 
 *          History: Sep 29, 2014 11:51:45 AM Administrator Created.
 * 
 */
public class StringUtils {
	private static MessageDigest digest = null;

//	public static String merge(String[] src, String delimiter) {
//		StringBuffer newSrc = new StringBuffer();
//		for (int i = 0; i < src.length; ++i)
//			if (i < src.length - 1)
//				newSrc.append(src[i]).append(delimiter);
//			else
//				newSrc.append(src[i]);
//
//		return newSrc.toString();
//	}
//
//	public static String[] split(String str, int sect, int len)
//			throws Exception {
//		String[] result = new String[sect];
//		int j = 0;
//		for (j = 0; j < sect; ++j)
//			result[j] = "";
//
//		for (j = 0; j < sect; ++j) {
//			if (str.length() < len * j)
//				break;
//			if (str.length() < len * (j + 1))
//				result[j] = str.substring(len * j, str.length());
//			else
//				result[j] = str.substring(len * j, len * (j + 1));
//		}
//
//		return result;
//	}
//
//	public static final String replace(String line, String oldString,
//			String newString) {
//		if (line == null)
//			return null;
//
//		int i = 0;
//
//		if ((i = line.indexOf(oldString, i)) >= 0) {
//			char[] line2 = line.toCharArray();
//			char[] newString2 = newString.toCharArray();
//			int oLength = oldString.length();
//			StringBuffer buf = new StringBuffer(line2.length);
//			buf.append(line2, 0, i).append(newString2);
//			i += oLength;
//			int j = i;
//			while ((i = line.indexOf(oldString, i)) > 0) {
//				buf.append(line2, j, i - j).append(newString2);
//				i += oLength;
//				j = i;
//			}
//			buf.append(line2, j, line2.length - j);
//			return buf.toString();
//		}
//
//		return line;
//	}
//
//	public static final String replace(String line, String oldString,
//			String newString, int[] count) {
//		if (line == null)
//			return null;
//
//		int i = 0;
//		if ((i = line.indexOf(oldString, i)) >= 0) {
//			int counter = 0;
//			++counter;
//			char[] line2 = line.toCharArray();
//			char[] newString2 = newString.toCharArray();
//			int oLength = oldString.length();
//			StringBuffer buf = new StringBuffer(line2.length);
//			buf.append(line2, 0, i).append(newString2);
//			i += oLength;
//			int j = i;
//			while ((i = line.indexOf(oldString, i)) > 0) {
//				++counter;
//				buf.append(line2, j, i - j).append(newString2);
//				i += oLength;
//				j = i;
//			}
//			buf.append(line2, j, line2.length - j);
//			count[0] = counter;
//			return buf.toString();
//		}
//		return line;
//	}
//
//	public static final String replaceIgnoreCase(String line, String oldString,
//			String newString) {
//		if (line == null)
//			return null;
//
//		String lcLine = line.toLowerCase();
//		String lcOldString = oldString.toLowerCase();
//		int i = 0;
//		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
//			char[] line2 = line.toCharArray();
//			char[] newString2 = newString.toCharArray();
//			int oLength = oldString.length();
//			StringBuffer buf = new StringBuffer(line2.length);
//			buf.append(line2, 0, i).append(newString2);
//			i += oLength;
//			int j = i;
//			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
//				buf.append(line2, j, i - j).append(newString2);
//				i += oLength;
//				j = i;
//			}
//			buf.append(line2, j, line2.length - j);
//			return buf.toString();
//		}
//		return line;
//	}
//
//	public static int getStrIndex(String s, String[] args) {
//		int length = args.length;
//		for (int i = 0; i < length; ++i)
//			if (args[i].equals(s))
//				return i;
//
//		return -1;
//	}

	public static String nullToEmpty(String src) {
		if ((src == null) || (src.equalsIgnoreCase("NULL")))
			return "";
		return src;
	}

//	public static String toCamelCasing(String s) {
//		if (s == null) {
//			return s;
//		}
//
//		StringBuffer buffer = new StringBuffer();
//		for (int i = 0; i < s.length() - 1; ++i) {
//			char ch = s.charAt(i);
//			if (ch != '_') {
//				buffer.append(ch);
//			} else {
//				char nextChar = s.charAt(i + 1);
//				if (nextChar != '_') {
//					if (buffer.toString().length() < 2)
//						buffer.append(Character.toLowerCase(nextChar));
//					else
//						buffer.append(Character.toUpperCase(nextChar));
//
//					++i;
//				}
//			}
//		}
//		char lastChar = s.charAt(s.length() - 1);
//		if (lastChar != '_') {
//			buffer.append(lastChar);
//		}
//
//		return buffer.toString();
//	}

	public static String lowerFirstChar(String s) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(Character.toLowerCase(s.charAt(0)))
				.append(s.substring(1));

		return buffer.toString();
	}

	public static String upperFirstChar(String s) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(Character.toUpperCase(s.charAt(0)))
				.append(s.substring(1));

		return buffer.toString();
	}

	/**
	 * 将str将多个分隔符进行切分，
	 * 
	 * 示例：StringTokenizerUtils.split("1,2;3 4"," ,;"); 返回: ["1","2","3","4"]
	 * 
	 * @param str
	 * @param seperators
	 * @return
	 */
	@SuppressWarnings("all")
	public static String[] split(String str, String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
		List result = new ArrayList();

		while (tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return (String[]) result.toArray(new String[result.size()]);
	}

//	/**
//	 * 右补位，左对齐
//	 *
//	 * @param oriStr
//	 *            原字符串
//	 * @param len
//	 *            目标字符串长度
//	 * @param alexin
//	 *            补位字符
//	 * @return 目标字符串
//	 */
//	public static String padRight(String oriStr, int len, char alexin) {
//		String str = "";
//		int strlen = oriStr.length();
//		if (strlen < len) {
//			for (int i = 0; i < len - strlen; i++) {
//				str = str + alexin;
//			}
//		}
//		str = str + oriStr;
//		return str;
//	}
//
//	/**
//	 * 左补位，右对齐
//	 *
//	 * @param oriStr
//	 *            原字符串
//	 * @param len
//	 *            目标字符串长度
//	 * @param alexin
//	 *            补位字符
//	 * @return 目标字符串
//	 */
//	public static String padLeft(String oriStr, int len, char alexin) {
//		String str = "";
//		int strlen = oriStr.length();
//		if (strlen < len) {
//			for (int i = 0; i < len - strlen; i++) {
//				str = str + alexin;
//			}
//		}
//		str = oriStr + str;
//		return str;
//	}
	
	/* 判断对象是否Empty(null或元素为0)<br>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 * 
	 * @param pObj
	 *            待检查对象
	 * @return boolean 返回的布尔值
	 */
	public static boolean isEmpty(Object pObj) {
		if (null == pObj || "".equals(pObj) ||  "null".equals( pObj))
			return true;
		if (pObj instanceof CharSequence) {
			if (((CharSequence) pObj).length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection<?>) pObj).isEmpty()) {
				return true;
			}
		} else if (pObj instanceof Map) {
			if (((Map<?,?>) pObj).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断对象是否为NotEmpty(!null或元素>0)<br>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 * 
	 * @param pObj
	 *            待检查对象
	 * @return boolean 返回的布尔值
	 */
	public static boolean isNotEmpty(Object pObj) {
		if (null == pObj || "".equals(pObj))
			return false;
		if (pObj instanceof String) {
			if (((String) pObj).trim().length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection<?>) pObj).isEmpty()) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map<?,?>) pObj).isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isEmpty(String pObj) {
		if (null == pObj || "".equals(pObj) ||  "null".equals(pObj))
			return true;
		return false;
	}
	
	public static boolean isNotEmpty(String pObj) {
		if (null == pObj || "".equals(pObj) ||  "null".equals(pObj))
			return false;
		return true;
	}
	
	
	/**
	 * 
	 * @author Fan JiuYi
	 * @date：2016-5-29 下午01:26:16
	 * @Description: (判断数组内是否存在 该字符串)
	 * @version 1.0
	 * @param strArray 目标数组
	 * @param str 目标字符串
	 * @return boolean值
	 */
	public static boolean isExist(String[] strArray, String str){
		boolean isExist =false;
		if(!isNotEmpty(str)){
			return isExist;
		}
		for(String st : strArray){
			if(str.equals(st)){
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	/**
     * 检查指定的字符串列表是否不为空。
     */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}


	/**
	 * Is exist empty boolean.
	 *
	 * @param values the values
	 * @return the boolean
	 */
	public static boolean isExistEmpty(String... values) {
		if (values == null || values.length == 0) {
			return true;
		} else {
			for (String value : values) {
				if(isEmpty(value)){
					return true;
				}
			}
		}
		return false;
	}
	
	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	@SuppressWarnings("unchecked")
	public static Map<String, String> transBean2Map(Object obj) {

		if(obj == null){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if(StringUtils.isNotEmpty(value)){
						map.put(key, String.valueOf(value));
					}
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e.getLocalizedMessage());
		}

		return map;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject transBean2JsonObj(Object obj) {
		if(obj == null){
			return null;
		}
		JSONObject map = new JSONObject();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if(StringUtils.isNotEmpty(value)){
						map.put(key, String.valueOf(value));
					}
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2JsonObj Error " + e.getLocalizedMessage());
		}

		return map;
	}
	
	/**
	 * 将map转换成url
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getUrlParamsByMap(Map<String, ?> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, ?> entry : map.entrySet()) {
			String vStr = "";
			try {
				sb.append("&");
				if(StringUtils.isNotEmpty(entry.getValue())){
					vStr = String.valueOf(entry.getValue());
				}
				sb.append(entry.getKey() + "=" + urlEncoder(vStr));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String s = sb.deleteCharAt(0).toString();
		return s;
	}

	public static String urlEncoder(String s){
		try {
			return  URLEncoder.encode(s,"utf-8");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			return s;
		}
	}

	public static String urlDecoder(String s){
		try {
			return  URLDecoder.decode(s,"utf-8");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public static String trim(String str,String defaultVal) {
        return str != null ? str.trim() : defaultVal;
    }
	
	
	public static String trim(final String str) {
        return str == null ? null : str.trim();
    }
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	/**
	 * 转换为Integer类型
	 */
	public static int toInt(Object val){
		return toLong(val).intValue();
	}

	/**
	 * 获取去掉横线的长度为32的UUID串.
	 *
	 * @author WuShuicheng.
	 * @return uuid.
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获取带横线的长度为36的UUID串.
	 *
	 * @return uuid.
	 */
	public static String get36UUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 功能：判断一个字符串是否包含特殊字符
	 * @param str 要判断的字符串
	 * @return true 提供的参数string不包含特殊字符
	 * @return false 提供的参数string包含特殊字符
	 */
	public static boolean isConSpeCharacters(String str) {
		if(str.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length()==0){
			//如果不包含特殊字符
			return true;
		}
		return false;
	}

	/**
	 * Trim con spe characters string.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String trimConSpeCharacters(String str) {
		if(isNotEmpty(str)){// [`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]
			str = str.replaceAll("[`~!@#$%^&*='',\\\\[\\\\]<>?~！@#￥%……&*（）【】‘；：”“’。，、？]", " ");
		}
		return str;
	}

	/**
	 *
	 * @param str
	 * @return
     */
	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * Is contains key boolean.
	 *
	 * @param str   the str
	 * @param infos the infos
	 * @return the boolean
	 */
	public static boolean isContainsKey(String str,String ... infos){
		if(StringUtils.isEmpty(str) || StringUtils.isEmpty(infos)){
			return false;
		}
		for (int i=0;i<infos.length;i++){
			if (str.indexOf(infos[i])>-1){
				return true;
			}
		}
		return false;
	}

	public static String urlReplaceEncoder(String str){
		/**
		 * +    URL 中+号表示空格                                 %2B
		 空格 URL中的空格可以用+号或者编码           %20
		 /   分隔目录和子目录                                     %2F
		 ?    分隔实际的URL和参数                             %3F
		 %    指定特殊字符                                          %25
		 #    表示书签                                                  %23
		 &    URL 中指定的参数间的分隔符                  %26
		 =    URL 中指定参数的值                                %3D
         */
		if(StringUtils.isEmpty(str)){
			return str;
		}
		str = str.replace("+","%2B");
				//.replace(" ","%20")
				//.replace("/","%2F").replace("?","%3F")
				//.replace("%","%25").replace("#","%23")
				//.replace("&","%26").replace("=","%3D");
		return str;
	}

	public static String urlReplaceDecoder(String str){
		/**
		 * +    URL 中+号表示空格                                 %2B
		 空格 URL中的空格可以用+号或者编码           %20
		 /   分隔目录和子目录                                     %2F
		 ?    分隔实际的URL和参数                             %3F
		 %    指定特殊字符                                          %25
		 #    表示书签                                                  %23
		 &    URL 中指定的参数间的分隔符                  %26
		 =    URL 中指定参数的值                                %3D
		 */
		if(StringUtils.isEmpty(str)){
			return str;
		}
		str = str.replace("%2B","+");
				//.replace("%20"," ")
				//.replace("%2F","/").replace("%3F","?")
				//.replace("%25","%").replace("%23","#")
				//.replace("%26","&").replace("%3D","=");
		return str;
	}

	/**
	 *
	 * @param v
	 * @param len
     * @return
     */
	public static String getLeft(String v , int len){
		if(v ==null || v.length()==0){
			return "";
		}
		if(v.length()>len){
			v = v.substring(0,len);
		}
		return v;
	}


	/**
	 * @param inParamJO
	 * @param keys
	 * @return
	 */
	public static boolean areNotEmpty(JSONObject inParamJO, String... keys) {
		//--非空数据校验;
		for(int i=0; i<keys.length;i++){
			String key = keys[i];
			if(!inParamJO.containsKey(key)){
				return false;
			}
			if(StringUtils.isEmpty(inParamJO.getString(key))){
				return false;
			}
		}
		return true;
	}

	public static int lookIndex(String str,char c , int index){
		int number = 0;
		char arr[] = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == c) {
				number++;
			}
			if (number == index) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取数据
	 * @param v
	 * @param defaultVal
     * @return
     */
	public static String getVal(String v , String  defaultVal){
		return isEmpty(v) ? defaultVal : v;
	}


	public static void main(String[] args) {
		System.out.println(StringUtils.trimConSpeCharacters("[cccc-hh_34;00:你—好+=|{}]"));
	}
	
}
