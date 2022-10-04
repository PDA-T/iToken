package com.pda.itoken.service.redis.service.impl;

import com.pda.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * RedisService业务层处理
 */
@Service
public class RedisServiceImpl implements RedisService {

	// redis工具类
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 新增
	 * @param key
	 * @param value
	 * @param seconds 超时时间
	 */
	@Override
	public void put(String key, Object value, long seconds) {
		redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
	}

	/**
	 * 获取
	 * @param key
	 */
	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
}
