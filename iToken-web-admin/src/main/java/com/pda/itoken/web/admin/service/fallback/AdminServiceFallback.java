package com.pda.itoken.web.admin.service.fallback;

import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.hystrix.Fallback;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * 熔断器
 */
@Component
public class AdminServiceFallback implements AdminService {
	/**
	 * @author PDA
	 * @Date 2022/10/16 19:33
	 * @Description 根据 ID 获取管理员
	 * @Param [userCode]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@Override
	public String get(String userCode) {
		try {
			String json = MapperUtils.obj2json(new TbSysUser());
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:33
	 * @Description 保存管理员
	 * @Param [tbSysUserJson, optsBy]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@Override
	public String save(String tbSysUserJson, String optsBy) {
		return Fallback.badGateway();
	}

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:33
	 * @Description 分页查询
	 * @Param [pageNum, pageSize, tbSysUserJson]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@Override
	public String page(int pageNum, int pageSize, String tbSysUserJson) {
		return Fallback.badGateway();
	}
}
