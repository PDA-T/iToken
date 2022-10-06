package com.pda.itoken.service.admin.service.impl;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.mapper.TbSysUserMapper;
import com.pda.itoken.common.service.impl.BaseServiceImpl;
import com.pda.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户Service业务层处理
 */
@Service
@Transactional(readOnly = true)// 开启只读
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {
}
