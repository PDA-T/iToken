package com.pda.itoken.web.admin.service.fallback;

import com.pda.itoken.common.hystrix.Fallback;
import com.pda.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * 熔断器
 */
@Component
public class AdminServiceFallback implements AdminService {
	/**
	 * 登陆熔断
	 *
	 * @param loginCode
	 * @param password
	 * @return
	 */
	@Override
	public String login(String loginCode, String password) {
		return Fallback.badGateway();
	}
}
