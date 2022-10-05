package com.pda.itoken.service.admin.controller;

import com.pda.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 */
@RestController
public class AdminController {

	// 用户
	@Autowired
	private AdminService adminService;
}
