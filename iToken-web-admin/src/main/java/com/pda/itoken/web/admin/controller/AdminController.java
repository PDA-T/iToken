package com.pda.itoken.web.admin.controller;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.web.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	 * @author PDA
	 * @Date 2022/10/16 19:37
	 * @Description ModelAttribute方法会在RequestMapping之前执行
	 * @Param [userCode]
	 * @return com.pda.itoken.common.domain.TbSysUser
	 * @since version-1.0
	 */
	@ModelAttribute
	public TbSysUser tbSysUser(String userCode){
		TbSysUser tbSysUser = null;
		if (StringUtils.isBlank(userCode)){
			tbSysUser = new TbSysUser();
		}else {
			String json = adminService.get(userCode);
			try {
				tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return tbSysUser;
	}

	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping(value = {"","index"},method = RequestMethod.GET)
	public String index(){
		return "index";
	}
}
