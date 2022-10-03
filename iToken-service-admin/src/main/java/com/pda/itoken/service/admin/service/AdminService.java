package com.pda.itoken.service.admin.service;

import com.pda.itoken.service.admin.domain.TbSysUser;

/**
 * 用户Service接口
 */
public interface AdminService {
	/**
	 * 注册
	 * @param tbSysUser
	 */
	public void register(TbSysUser tbSysUser);

	/**
	 * 登陆
	 * @param loginCode 登陆账号
	 * @param plantPassword 明文密码
	 * @return
	 */
	public TbSysUser login(String loginCode,String plantPassword);
}
