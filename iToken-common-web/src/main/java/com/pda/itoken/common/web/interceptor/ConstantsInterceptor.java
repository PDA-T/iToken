package com.pda.itoken.common.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 初始化常量拦截器
 */
public class ConstantsInterceptor implements HandlerInterceptor {
	// cdn地址
	private static final String HOST_CDN = "http://192.168.60.129:81";

	// 资源地址
	private static final String TEMPLATE_ADMIN_LTE = "/adminlte/v2.4.3";

	/**
	 * Controller方法处理之前调用
	 * 若返回false,则中断执行,不会进入afterCompletion
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	/**
	 * 调用前提:preHandle返回true
	 * Controller方法处理完之后调用
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null){
			modelAndView.addObject("adminlte",HOST_CDN + TEMPLATE_ADMIN_LTE);
		}
	}

	/**
	 * 调用前提:preHandle返回true
	 * DispatcherServlet进行视图的渲染之后调用
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
