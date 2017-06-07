package com.sh.gateway.controller.base;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;


/**
 * The type Base controller.
 */
public abstract class BaseController {


	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
	}

	/**
	 * 获取服务名
	 * @param actionname
     * @return
     */
	protected String getServiceName(String actionname){
		String beanName = null;
		beanName = "Api"+ actionname +"InvokeImpl";
		return beanName;
	}

	/**
	 * 异步通知调用服务
	 * @param paychannel
	 * @param service
     * @return
     */
	protected String getNotifyServiceName(String paychannel,String service){
		String beanName = null;
		beanName = paychannel  + service+ "Notify" + "InvokeImpl";
		return beanName;
	}

//	/**
//	 * 刷新本地配置;
//	 * @param req
//	 * @param resp
//     */
//	public void refresh_system_base(HttpServletRequest req, HttpServletResponse resp) {
//		String sign = req.getParameter("sign");
//		if(StringUtils.isEmpty(sign)){
//			HtmlUtil.writerHtml(resp, "refresh_system: sign is not null");
//			return;
//		}
//		log.info("refresh_system init - base");
//		ConfigUtil.init();
//		SignUtil.initOrgPlatform();
//		BaofooConfig.clear();
//		log.info("refresh_system success");
//		HtmlUtil.writerHtml(resp, "success");
//	}
	
}
