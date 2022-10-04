package com.pda.itoken.service.sso.service.consumer.fallback;

import com.google.common.collect.Lists;
import com.pda.itoken.common.constants.HttpStatusConstants;
import com.pda.itoken.common.dto.BaseResult;
import com.pda.itoken.common.hystrix.Fallback;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.service.sso.service.consumer.RedisService;
import org.springframework.stereotype.Component;

/**
 * Redis熔断器
 */
@Component
public class RedisServiceFallback implements RedisService {
	/**
	 * 新增熔断
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	@Override
	public String put(String key, String value, long seconds) {
		return Fallback.badGateway();
	}

	/**
	 * 获取熔断
	 * @param key
	 * @return
	 */
	@Override
	public String get(String key) {
		return Fallback.badGateway();
	}
}
