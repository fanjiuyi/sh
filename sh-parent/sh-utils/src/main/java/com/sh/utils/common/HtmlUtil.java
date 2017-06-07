package com.sh.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>罗泽军<br>
 * <b>日期：</b> Dec 14, 2011 <br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class HtmlUtil {
	/**
	 * The constant log.
	 */
	public static final Logger log = LoggerFactory.getLogger(HtmlUtil.class);

	/**
	 * <br>
	 * <b>功能：</b>输出json格式<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Dec 14, 2011 <br>
	 *
	 * @param response the response
	 * @param jsonStr  the json str
	 * @throws Exception
	 */
	public static void writerJson(HttpServletResponse response, String jsonStr) {
		//response.setContentType("application/json");
		response.setHeader("Content-type","application/json;charset=UTF-8");
		writer(response,jsonStr);
	}

	/**
	 * Writer json.
	 *
	 * @param response the response
	 * @param object   the object
	 */
	public static void writerJson(HttpServletResponse response, Object object){
		writerJson(response, JsonUtils.toJsonString(object));
	}

	/**
	 * <br>
	 * <b>功能：</b>输出HTML代码<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Dec 14, 2011 <br>
	 *
	 * @param response the response
	 * @param htmlStr  the html str
	 * @throws Exception
	 */
	public static void writerHtml(HttpServletResponse response, String htmlStr) {
		response.setHeader("Content-type","text/html;charset=UTF-8");
		writer(response,htmlStr);
	}

	/**
	 * Writer xml.
	 *
	 * @param response the response
	 * @param htmlStr  the html str
	 */
	public static void writerXml(HttpServletResponse response, String htmlStr) {
		response.setHeader("Content-type","text/xml;charset=UTF-8");
		writer(response,htmlStr);
	}

	/**
	 * Writer html.
	 *
	 * @param response the response
	 * @param object   the object
	 */
	public static void writerHtml(HttpServletResponse response, Object object) {
		writerHtml(response, JsonUtils.toJsonString(object));
	}

	/**
	 * shuchu
	 *
	 * @param response the response
	 * @param str      the str
	 */
	public static void writer(HttpServletResponse response, String str){
		try {
			//设置页面不缓存
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out= null;
			out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("writer faild： {}",e.getMessage());
		}
	}
}
