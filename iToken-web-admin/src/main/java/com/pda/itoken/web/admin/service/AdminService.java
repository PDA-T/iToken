package com.pda.itoken.web.admin.service;

import com.pda.itoken.common.web.service.BaseClientService;
import com.pda.itoken.web.admin.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 消费者接口
 */
@FeignClient(value = "iToken-service-admin",fallback = AdminServiceFallback.class)// 绑定iToken-service-admin服务,熔断回调类
public interface AdminService extends BaseClientService {
	/**
	 * @author PDA
	 * @Date 2022/10/16 19:33
	 * @Description 根据 ID 获取管理员
	 * @Param [userCode]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "v1/admins", method = RequestMethod.GET)
	public String get(@RequestParam(required = true, value = "userCode") String userCode);

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:33
	 * @Description 保存管理员
	 * @Param [tbSysUserJson, optsBy]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "v1/admins", method = RequestMethod.POST)
	public String save(@RequestParam(required = true, value = "tbSysUserJson") String tbSysUserJson,
					   @RequestParam(required = true, value = "optsBy") String optsBy);

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:33
	 * @Description 分页查询
	 * @Param [pageNum, pageSize, tbSysUserJson]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "v1/admins/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
					   @PathVariable(required = true, value = "pageSize") int pageSize,
					   @RequestParam(required = false, value = "tbSysUserJson") String tbSysUserJson);
}
