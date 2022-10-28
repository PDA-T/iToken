package com.pda.itoken.web.backend.service.fallback;

import com.pda.itoken.web.backend.service.RedisService;
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
		return null;
	}

	/**
	 * 获取熔断
	 * @param key
	 * @return
	 */
	@Override
	public String get(String key) {
		return null;
	}
}
