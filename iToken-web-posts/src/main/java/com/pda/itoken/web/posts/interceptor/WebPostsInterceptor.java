package com.pda.itoken.web.posts.interceptor;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.utils.CookieUtils;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.common.web.constants.WebConstants;
import com.pda.itoken.common.web.utils.HttpServletUtils;
import com.pda.itoken.web.posts.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class WebPostsInterceptor implements HandlerInterceptor {

	// Redis
	@Autowired
	private RedisService redisService;

	// yml配置
	@Value(value = "${hosts.sso}")
	private String hosts_sso;

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
		String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
		// 未登录
		if (!StringUtils.isNotBlank(token)){
			// 重定向
			String url = String.format("%s/login?url=%s", hosts_sso, HttpServletUtils.getFullPath(request));
			response.sendRedirect(url);
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
		TbSysUser tbSysUser = (TbSysUser) session.getAttribute(WebConstants.SESSION_USER);
		// 已登陆
		if (tbSysUser != null){
			if (modelAndView != null){
				modelAndView.addObject(WebConstants.SESSION_USER,tbSysUser);
			}
		}else {
			// 未登录
			String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
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
							modelAndView.addObject(WebConstants.SESSION_USER,tbSysUser);
						}
						// 放入局部会话
						request.getSession().setAttribute(WebConstants.SESSION_USER,tbSysUser);
					}
				}
			}
		}
		// 确认是否登陆
		if (tbSysUser == null){
			response.sendRedirect(String.format("$s/login?url=%s",hosts_sso, HttpServletUtils.getFullPath(request)));
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
