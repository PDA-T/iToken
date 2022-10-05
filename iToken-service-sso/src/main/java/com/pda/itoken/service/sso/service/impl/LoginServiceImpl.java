package com.pda.itoken.service.sso.service.impl;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.service.sso.mapper.TbSysUserMapper;
import com.pda.itoken.service.sso.service.LoginService;
import com.pda.itoken.service.sso.service.consumer.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 登陆Service业务层处理
 */
@Service
public class LoginServiceImpl implements LoginService {

	// 日志
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	// 用户
	@Autowired
	private TbSysUserMapper tbSysUserMapper;

	// Redis
	@Autowired
	private RedisService redisService;

	/**
	 * 登陆
	 * @param loginCode 登陆账号
	 * @param plantPassword 明文密码
	 * @return
	 */
	@Override
	public TbSysUser login(String loginCode, String plantPassword) {
		TbSysUser tbSysUser = null;
		// 查询登陆信息缓存
		String json = redisService.get(loginCode);
		// 缓存无用户信息
		if (json == null){
			// 条件构造器
			Example example = new Example(TbSysUser.class);
			example.createCriteria().andEqualTo("loginCode",loginCode);
			// 查询
			tbSysUser = (TbSysUser) tbSysUserMapper.selectOneByExample(example);
			// 加密密码
			String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
			// 密码匹配
			if (tbSysUser != null && password.equals(tbSysUser.getPassword())){
				try {
					// 缓存新增用户信息
					redisService.put(loginCode,MapperUtils.obj2json(tbSysUser),60*60*24);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return tbSysUser;
			}else {
				// 密码不匹配
				return null;
			}
		}else {
			// 缓存中有用户信息
			try {
				tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
			} catch (Exception e) {
				LOGGER.warn("触发熔断:{}",e.getMessage());
			}
		}
		return tbSysUser;
	}
}
