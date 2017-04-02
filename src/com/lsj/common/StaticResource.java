package com.lsj.common;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class StaticResource {
	/**
	 * Url相关信息
	 */
	public static final Map<String, Map<String, Object>> urls = new HashMap<String, Map<String, Object>>();
	
	/**
	 * Jedis相关信息
	 */
	public static final JedisPool jedisPool;
	public static final String redishost = "127.0.0.1";
	public static final int redisport = 6379;
	
	static{
		JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);        //总连接数
        poolConfig.setMaxIdle(10);          //空闲连接数
        poolConfig.setMaxWaitMillis(3000);  //创建连接的超时时间
        poolConfig.setTestOnBorrow(true);   //在创建连接时会测试
        jedisPool = new JedisPool(poolConfig, StaticResource.redishost, StaticResource.redisport);
	}
}
