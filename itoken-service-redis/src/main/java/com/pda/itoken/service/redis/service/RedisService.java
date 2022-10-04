package com.pda.itoken.service.redis.service;

/**
 * RedisService接口
 */
public interface RedisService {
	/**
	 * 新增
	 * @param key
	 * @param value
	 * @param seconds 超时时间
	 */
	public void put(String key,Object value,long seconds);

	/**
	 * 获取
	 * @param key
	 */
	public Object get(String key);
}
