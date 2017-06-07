package com.sh.gateway.core.inteceptor;

import com.sh.utils.logger.LogHelper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  
 *  @author Fan JiuYi
 *	@date：2015-7-24 下午02:01:42 
 *	@Description: TODO(springMVC拦截器) 
 *  @version 1.0
 *
 */
public class TransactionalInteceptor implements HandlerInterceptor{

	/**
	 *  @author Fan JiuYi
	 *	@date：2015-7-24 下午02:53:44 
	 *	@Description: TODO( Action之前执行) 
	 *  @version 1.0
	 *
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		LogHelper.debug("【1号拦截器】---->请求交与Controller前拦截进行处理:................");
		LogHelper.debug("【1号拦截器】---->拦截器逻辑处理完成将请求转发至Controole。url:"+arg0.getRequestURI());
		return true;
		
	}
	
	
	
	
	/**
	 *  @author Fan JiuYi
	 *	@date：2015-7-24 下午02:53:44 
	 *	@Description: TODO(生成视图之前执行) 
	 *  @version 1.0
	 *
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		LogHelper.debug("【2号拦截器】---->请求交与View前拦截进行处理:................");
		LogHelper.debug("【2号拦截器】---->拦截器逻辑处理完成将请求转发至View进行视图渲染。url:"+arg0.getRequestURI());
		
	}
	
	
	
	

	/**
	 *  @author Fan JiuYi
	 *	@date：2015-7-24 下午02:53:44 
	 *	@Description: TODO(最后执行,可用于释放资源) 
	 *  @version 1.0
	 *
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
	throws Exception {
		LogHelper.debug("【3号拦截器】---->本次请求处理完成进行扫尾:................");
		LogHelper.debug("【3号拦截器】---->本次请求处理完成进行扫尾完成。url:"+arg0.getRequestURI());
	}
}
