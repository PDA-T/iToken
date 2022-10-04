package com.pda.itoken.service.admin.controller;

import com.google.common.collect.Lists;
import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.dto.BaseResult;
import com.pda.itoken.service.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 */
@RestController
public class AdminController {

	// 用户
	@Autowired
	private AdminService adminService;

	/**
	 * 登陆
	 * @param loginCode
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public BaseResult login(String loginCode,String password){
		// 检查登陆信息
		BaseResult baseResult = checkLogin(loginCode, password);
		// 失败
		if (baseResult != null){
			return baseResult;
		}

		// 登陆
		TbSysUser tbSysUser = adminService.login(loginCode, password);
		if (tbSysUser != null){
			// 登陆成功
			return BaseResult.ok(tbSysUser);
		}else {
			// 登陆失败
			return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("","登陆失败")));
		}
	}

	/**
	 * 检查登陆
	 * @param loginCode
	 * @param password
	 * @return
	 */
	private BaseResult checkLogin(String loginCode,String password){
		BaseResult baseResult = null;

		if (StringUtils.isBlank(loginCode) || StringUtils.isBlank(password)){
			baseResult = BaseResult.notOk(Lists.newArrayList(
					new BaseResult.Error("loginCode","登陆账号为空"),
					new BaseResult.Error("password","登陆密码为空")
			));
		}
		return baseResult;
	}
}
