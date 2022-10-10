package com.pda.itoken.common.context;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author PDA
 * @Date 2022/10/10 19:28
 * @Description 获取Spring上下文
 * @since version-1.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextHolder.class);

	private static ApplicationContext applicationContext;

	/***
	 * @author PDA
	 * @Date 2022/10/10 19:32
	 * @Description 获取存储在静态变量中的ApplicationContext
	 * @return org.springframework.context.ApplicationContext
	 * @since version-1.0
	 */
	public static ApplicationContext getApplicationContext(){
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 19:34
	 * @Description 从静态变量 applicationContext 中获取 Bean,自动转型成所赋值的对象
	 * @Param [name]
	 * @return T
	 * @since version-1.0
	 */
	public static <T> T getBean(String name){
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 19:35
	 * @Description 从静态变量 applicationContext 中获取 Bean,自动转型成所赋值的对象
	 * @Param [clazz]
	 * @return T
	 * @since version-1.0
	 */
	public static <T> T getBean(Class<T> clazz){
		assertContextInjected();
		return applicationContext.getBean(clazz);
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 19:37
	 * @Description 实现 DisposableBean 接口,在 Context 关闭时清理静态变量
	 * @since version-1.0
	 * @throws Exception
	 */
	public void destroy() throws Exception{
		LOGGER.debug("清除 SpringContext 中的 applicationContext:{}",applicationContext);
		applicationContext = null;
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 19:39
	 * @Description 实现 ApplicationContextAware 接口,注入 Context 到静态变量中
	 * @Param [applicationContext]
	 * @since version-1.0
	 * @throws BeansException
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		ApplicationContextHolder.applicationContext = applicationContext;
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 19:42
	 * @Description 断言 Context 已经注入
	 * @since version-1.0
	 */
	private static void assertContextInjected(){
		Validate.validState(applicationContext != null,"applicationContext 属性未注入,请在 spring-context.xml 配置中定义 ApplicationContextHolder");
	}
}
