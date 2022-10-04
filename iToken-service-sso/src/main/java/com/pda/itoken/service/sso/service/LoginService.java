package com.pda.itoken.service.sso.service;

import com.pda.itoken.common.domain.TbSysUser;

/**
 * 登陆Service接口
 */
public interface LoginService {
	/**
	 * 登陆
	 * @param loginCode 登陆账号
	 * @param plantPassword 明文密码
	 * @return
	 */
	public TbSysUser login(String loginCode,String plantPassword);
}
