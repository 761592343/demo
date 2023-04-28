package com.yq.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisConnPool {
    private final JedisPool pool = new JedisPool("127.0.0.1", 6379);

    private static volatile RedisConnPool INSTANCE;

    public static Jedis getResource() {
        if (INSTANCE == null) {
            synchronized (RedisConnPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RedisConnPool();
                }
            }
        }

        return INSTANCE.pool.getResource();
    }
}
