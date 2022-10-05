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
	 * 跳转首页
	 * @return
	 */
	@RequestMapping(value = {"","index"},method = RequestMethod.GET)
	public String index(){
		return "index";
	}
}
