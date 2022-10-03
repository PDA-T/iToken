package com.pda.itoken.web.admin.controller;

import com.pda.itoken.web.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面控制器
 */
@Controller
public class AdminController {
	// 用户
	@Autowired
	private AdminService adminService;

	/**
	 * 跳转登陆页
	 * @return
	 */
	@RequestMapping(value = {"","login"},method = RequestMethod.GET)
	public String login(){
		String login = adminService.login("", "");
		System.out.println(login);
		return "index";
	}
}
