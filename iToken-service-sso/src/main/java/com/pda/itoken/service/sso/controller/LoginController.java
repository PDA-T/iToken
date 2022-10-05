package com.pda.itoken.service.sso.controller;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.utils.CookieUtils;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.service.sso.service.LoginService;
import com.pda.itoken.service.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登陆控制器
 */
@Controller
public class LoginController {

	// 登陆
	@Autowired
	private LoginService loginService;

	// Redis
	@Autowired
	private RedisService redisService;

	/**
	 * 跳转登录页
	 * @return
	 */
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String login(@RequestParam(required = false) String url,// 非必填
						HttpServletRequest request,Model model){
		String token = CookieUtils.getCookieValue(request, "token");
		// token不为空
		if (StringUtils.isNotBlank(token)){
			// 查询redis是否有token对应的登陆账号
			String loginCode = redisService.get(token);
			if (StringUtils.isNotBlank(loginCode)){
				// 查询redis中登陆账号对应的用户信息
				String json = redisService.get(loginCode);
				if (StringUtils.isNotBlank(json)){
					try {
						// 获取登陆信息
						TbSysUser tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
						// 已登陆
						if (tbSysUser != null){
							// 从其他链接进入重定向到进入链接
							if (StringUtils.isNotBlank(url)){
								return "redirect:" + url;
							}
						}
						// 登陆信息传入登陆页
						model.addAttribute("tbSysUser",tbSysUser);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return "login";
	}

	/**
	 * 登陆
	 * @param loginCode
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String login(@RequestParam(required = true) String loginCode,
						@RequestParam(required = true)String password,
						@RequestParam(required = false) String url,// 非必填
						HttpServletRequest request,
						HttpServletResponse response,
						RedirectAttributes redirectAttributes){
		// 登陆
		TbSysUser tbSysUser = loginService.login(loginCode, password);
		// 用户名密码错误
		if (tbSysUser == null){
			redirectAttributes.addFlashAttribute("message","用户名或密码错误");
		}else {
			// 生成token
			String token = UUID.randomUUID().toString();
			// token放入缓存
			String result = redisService.put(token, loginCode, 60 * 60 * 24);
			if (StringUtils.isNotBlank(result) && result.equals("ok")){
				// token放入Cookie
				CookieUtils.setCookie(request,response,"token",token,60 * 60 * 24);
				// url不为空重定向
				if (StringUtils.isNotBlank(url)){
					return "redirect:" + url;
				}
			}else {
				// 放入缓存失败
				redirectAttributes.addFlashAttribute("message","服务器异常");
			}
		}
		return "redirect:/login";
	}
}
