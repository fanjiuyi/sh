package com.sh.gateway.core.utils.web;

import com.alibaba.fastjson.JSONObject;
import com.sh.gateway.core.model.RetJSONObject;
import com.sh.utils.common.HtmlUtil;
import com.sh.utils.common.StringUtils;
import com.sh.utils.logger.LogHelper;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class ReturnDataUtil {
	

	public static String buildErrorForm(JSONObject paramJson, RetJSONObject ret){
		StringBuffer sbHtml = new StringBuffer();
		
//		String jumpTPlatUrl = ConfigUtil.getConfig(SignUtil.buildKey(paramJson)+".errorUrl");
		String jumpTPlatUrl = "";
				String type = "type=\"hidden\"";
		if(StringUtils.isEmpty(jumpTPlatUrl)){
			jumpTPlatUrl = "";
			type = "type=\"text\"";
		}

        sbHtml.append("<form id=\"targetsubmit\" name=\"targetsubmit\" action=\"" + jumpTPlatUrl+ "\" method=\"post\">");
        
        sbHtml.append("<input "+type+" name=\"reqno\" value=\"" + ret.getString("reqno") + "\"/>");
        sbHtml.append("<input "+type+" name=\"rtncode\" value=\"" + ret.getString("rtncode") + "\"/>");
        sbHtml.append("<input "+type+" name=\"rtnmsg\" value=\"" + ret.getString("rtnmsg") + "\"/>");
        sbHtml.append("<input "+type+" name=\"terminalno\" value=\"" + paramJson.getString("terminalno") + "\"/>");

        sbHtml.append("</form>");

		if(StringUtils.isNotEmpty(jumpTPlatUrl)){
			sbHtml.append("<script>document.forms['targetsubmit'].submit();</script>");
		}else{
			LogHelper.info("请求流水号:" + paramJson.getString("reqno")+ " 跳转公共错误页面");
		}
		return sbHtml.toString();
	}

	public static ModelAndView writeReturn(JSONObject paramJson,
                                           RetJSONObject ret, HttpServletRequest req, HttpServletResponse res) {
		try {

			LogHelper.info("writeReturn:"+ret);
				HtmlUtil.writerHtml(res, ret.toString());
				return null;

		} catch (Exception e) {
			LogHelper.error("writeReturn fail:", e );
			ret.setErrorRet("000001","[返回数据输出控制异常]");
			HtmlUtil.writerHtml(res, ret.toString());
			return null;
		}
	}
	

}
