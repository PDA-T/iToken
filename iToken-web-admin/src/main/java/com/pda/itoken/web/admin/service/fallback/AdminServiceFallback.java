package com.pda.itoken.web.admin.service.fallback;

import com.google.common.collect.Lists;
import com.pda.itoken.common.constants.HttpStatusConstants;
import com.pda.itoken.common.dto.BaseResult;
import com.pda.itoken.common.utils.MapperUtils;
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
		BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
				new BaseResult.Error(
						String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()),
						HttpStatusConstants.BAD_GATEWAY.getContent()
				)
		));
		try {
			return MapperUtils.obj2json(baseResult);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
