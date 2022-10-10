package com.pda.itoken.common.utils;

import com.pda.itoken.common.context.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author PDA
 * @Date 2022/10/10 19:46
 * @Description Redis工具类
 * @since version-1.0
 */
public class RedisCache implements Cache {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final String id;
	private RedisTemplate redisTemplate;

	private static final long EXPIRE_TIME_IN_MINUTES = 30;

	public RedisCache(String id){
		if (id == null){
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	@Override
	public String getId(){
		return id;
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 20:00
	 * @Description Put query result to redis
	 * @Param [key, value]
	 * @since version-1.0
	 */
	@Override
	public void putObject(Object key,Object value){
		try {
			RedisTemplate redisTemplate = getRedisTemplate();
			ValueOperations opsForValue = redisTemplate.opsForValue();
			opsForValue.set(key,value,EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
			LOGGER.debug("Put query result to redis");
		}catch (Throwable t){
			LOGGER.error("Redis put failed",t);
		}
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 20:03
	 * @Description Get cached query result form redis
	 * @Param [key]
	 * @return java.lang.Object
	 * @since version-1.0
	 */
	@Override
	public Object getObject(Object key){
		try {
			RedisTemplate redisTemplate = getRedisTemplate();
			ValueOperations opsForValue = redisTemplate.opsForValue();
			LOGGER.debug("Get cached query result form redis");
			return opsForValue.get(key);
		}catch (Throwable t){
			LOGGER.error("Redis get failed,fail over to db",t);
			return null;
		}
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 20:05
	 * @Description Remove cached query result from redis
	 * @Param [key]
	 * @return java.lang.Object
	 * @since version-1.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object removeObject(Object key){
		try {
			RedisTemplate redisTemplate = getRedisTemplate();
			redisTemplate.delete(key);
			LOGGER.debug("Remove cached query result from redis");
		}catch (Throwable t){
			LOGGER.error("Redis remove failed",t);
		}
		return null;
	}

	/**
	 * @author PDA
	 * @Date 2022/10/10 20:09
	 * @Description Clears this cache instance
	 * @return void
	 * @since version-1.0
	 */
	@Override
	public void clear(){
		RedisTemplate redisTemplate = getRedisTemplate();
		redisTemplate.execute((RedisCallback) connection -> {
			connection.flushDb();
			return null;
		});
		LOGGER.debug("Clear all the cached query result from redis");
	}

	@Override
	public int getSize(){
		return 0;
	}

	@Override
	public ReadWriteLock getReadWriteLock(){
		return readWriteLock;
	}

	public RedisTemplate getRedisTemplate(){
		if (redisTemplate == null){
			redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
		}
		return redisTemplate;
	}
}
