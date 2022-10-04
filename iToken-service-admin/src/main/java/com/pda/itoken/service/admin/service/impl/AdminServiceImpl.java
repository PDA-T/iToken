package com.pda.itoken.service.admin.service.impl;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.service.admin.mapper.TbSysUserMapper;
import com.pda.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户Service业务层处理
 */
@Service
@Transactional(readOnly = true)// 开启只读
public class AdminServiceImpl implements AdminService {

	// 用户
	@Autowired
	private TbSysUserMapper tbSysUserMapper;

	/**
	 * 注册
	 * @param tbSysUser
	 */
	@Override
	@Transactional(readOnly = false)// 关闭只读
	public void register(TbSysUser tbSysUser) {
		// 加密密码
		tbSysUser.setPassword(DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes()));
		// 插入数据
		tbSysUserMapper.insert(tbSysUser);
	}

	/**
	 * 登陆
	 * @param loginCode 登陆账号
	 * @param plantPassword 明文密码
	 * @return
	 */
	@Override
	public TbSysUser login(String loginCode, String plantPassword) {
		// 条件构造器
		Example example = new Example(TbSysUser.class);
		example.createCriteria().andEqualTo("loginCode",loginCode);
		// 查询
		TbSysUser tbSysUser = (TbSysUser) tbSysUserMapper.selectOneByExample(example);
		// 加密密码
		String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
		// 密码匹配
		if (password.equals(tbSysUser.getPassword())){
			return tbSysUser;
		}
		return null;
	}
}
