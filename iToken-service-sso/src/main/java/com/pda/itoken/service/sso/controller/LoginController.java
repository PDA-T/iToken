package com.pda.itoken.service.sso.controller;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.service.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登陆控制器
 */
@Controller
public class LoginController {

	// 登陆
	@Autowired
	private LoginService loginService;

	/**
	 * 跳转登录页
	 * @return
	 */
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	/**
	 * 登陆
	 * @param loginCode
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String login(String loginCode,String password){
		TbSysUser tbSysUser = loginService.login(loginCode, password);
		System.out.println(tbSysUser);
		return "ok";
	}
}
