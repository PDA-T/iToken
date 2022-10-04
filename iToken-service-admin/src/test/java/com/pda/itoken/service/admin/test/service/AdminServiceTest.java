package com.pda.itoken.service.admin.test.service;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.service.admin.ServiceAdminApplication;
import com.pda.itoken.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)// 运行于Spring测试环境
@SpringBootTest(classes = ServiceAdminApplication.class)// 加载ServiceAdminApplication,启动容器
@ActiveProfiles(value = "dev")// 指定环境
@Transactional// 开启事务
@Rollback// 测试后回滚
public class AdminServiceTest {

	/**
	 * 用户
	 */
	@Autowired
	private AdminService adminService;

	/**
	 * 注册
	 */
	@Test
	public void register(){
		// 用户
		TbSysUser tbSysUser = new TbSysUser();
		// 主键
		tbSysUser.setUserCode(UUID.randomUUID().toString());
		// 登陆账号
		tbSysUser.setLoginCode("lusifer@pda.com");
		// 昵称
		tbSysUser.setUserName("Lusifer");
		// 密码
		tbSysUser.setPassword("123456");
		// 用户类型
		tbSysUser.setUserType("0");
		// 管理员类型
		tbSysUser.setMgrType("1");
		// 状态
		tbSysUser.setStatus("0");
		// 创建时间
		tbSysUser.setCreateDate(new Date());
		// 创建人
		tbSysUser.setCreateBy(tbSysUser.getUserCode());
		// 更新时间
		tbSysUser.setUpdateDate(new Date());
		// 更新人
		tbSysUser.setUpdateBy(tbSysUser.getUserCode());
		// 集团Code
		tbSysUser.setCorpCode("0");
		// 集团名
		tbSysUser.setCorpName("iToken");
		// 注册
		adminService.register(tbSysUser);
	}

	/**
	 * 登陆
	 */
	@Test
	public void login(){
		// 登陆
		TbSysUser tbSysUser = adminService.login("lusifer@pda.com", "123456");
		// 断言tbSysUser不为空
		Assert.assertNotNull(tbSysUser);
	}
}
