package com.pda.itoken.web.posts.service;

import com.pda.itoken.web.posts.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * RedisService接口
 */
@FeignClient(value = "iToken-service-redis",fallback = RedisServiceFallback.class)//绑定iToken-service-redis服务,熔断回调类
public interface RedisService {

	/**
	 * 新增
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	@RequestMapping(value = "put",method = RequestMethod.POST)
	public String put(@RequestParam(value = "key") String key,
					  @RequestParam(value = "value") String value,
					  @RequestParam(value = "seconds") long seconds);

	/**
	 * 获取
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "get",method = RequestMethod.GET)
	public String get(@RequestParam(value = "key") String key);
}
