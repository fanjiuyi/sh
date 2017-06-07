package com.sh.utils.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author Administrator
 * 
 */
public class SpringContextHolder implements ApplicationContextAware {
	
	static Logger log = LoggerFactory.getLogger(SpringContextHolder.class);

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 得到Spring 上下文环境
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 通过Spring Bean name 得到Bean
	 * 
	 * @param name
	 *            bean 上下文定义名称
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 通过Spring Bean name 得到Bean
	 * 
	 * @param name
	 *            bean 上下文定义名称
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean2(String name) {
		checkApplicationContext();
		try{
			return (T) applicationContext.getBean(name);
		}catch (Exception e) {
			log.error("getBean2:[{}] fail",name);
			return null;
		}
	}
	
	/**
	 * 获取bean; 首字母自动转大写
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean_Upper(String name) {
		checkApplicationContext();
		try{
			name = Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
			return (T) applicationContext.getBean(name);
		}catch (Exception e) {
			log.error("getBean2:[{}] fail",name);
			return null;
		}
	}

	/**
	 * 通过类型得到Bean
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException(
					"applicaitonContext未注入,请在application-context.xml中定义SpringContextHolder");
		}
	}

}
