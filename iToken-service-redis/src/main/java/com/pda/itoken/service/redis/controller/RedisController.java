package com.pda.itoken.service.redis.controller;

import com.pda.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis控制器
 */
@RestController
public class RedisController {

	@Autowired
	private RedisService redisService;

	/**
	 * 新增
	 * @param key
	 * @param value
	 * @param seconds
	 * @return
	 */
	@RequestMapping(value = "put",method = RequestMethod.POST)
	public String put(String key,String value,long seconds){
		redisService.put(key,value,seconds);
		return "ok";
	}

	/**
	 * 获取
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "get",method = RequestMethod.GET)
	public String get(String key){
		Object o = redisService.get(key);
		if (o != null){
			return String.valueOf(o);
		}
		return null;
	}
}
