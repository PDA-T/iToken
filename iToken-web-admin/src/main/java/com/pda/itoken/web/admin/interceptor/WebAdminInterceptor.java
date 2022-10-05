package com.pda.itoken.web.admin.interceptor;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.utils.CookieUtils;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.web.admin.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class WebAdminInterceptor implements HandlerInterceptor {

	// Redis
	@Autowired
	private RedisService redisService;

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
		// 查询是否有token
		String token = CookieUtils.getCookieValue(request, "token");
		// 未登录
		if (!StringUtils.isNotBlank(token)){
			// 重定向
			response.sendRedirect("http://127.0.0.1:8503/login?url=http://127.0.0.1:8601");
			return false;
		}
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
		// 获取会话
		HttpSession session = request.getSession();
		TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");
		// 已登陆
		if (tbSysUser != null){
			if (modelAndView != null){
				modelAndView.addObject("tbSysUser",tbSysUser);
			}
		}else {
			// 未登录
			String token = CookieUtils.getCookieValue(request, "token");
			if (StringUtils.isNotBlank(token)){
				// 查询redis是否有token对应的登陆账号
				String loginCode = redisService.get(token);
				if (StringUtils.isNotBlank(loginCode)){
					// 查询redis中登陆账号对应的用户信息
					String json = redisService.get(loginCode);
					if (StringUtils.isNotBlank(json)){
						// 已登录状态
						tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
						if (modelAndView != null){
							modelAndView.addObject("tbSysUser",tbSysUser);
						}
						// 放入局部会话
						request.getSession().setAttribute("tbSysUser",tbSysUser);
					}
				}
			}
		}
		// 确认是否登陆
		if (tbSysUser == null){
			response.sendRedirect("http://127.0.0.1:8503/login?url=http://127.0.0.1:8601");
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
