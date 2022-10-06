package com.pda.itoken.service.admin.mapper;

import com.pda.itoken.common.domain.TbSysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

/**
 * 用户扩展Mapper
 */
@Repository// 将接口的实现类交给spring管理
public interface TbSysUserExtendMapper extends MyMapper<TbSysUser> {
}