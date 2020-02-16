package com.taotao.rest.component;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author GJ1e
 * @Create 2020/2/11
 * @Time 21:55
 * Jedis单机版
 */
public class JedisClientSingle implements JedisClient{
    @Autowired
    JedisPool jedisPool;

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        String result = jedis.get(key);
        jedis.close();
        return result;

    }

    @Override
    public Long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        Long hset = jedis.hset(hkey, key, value);
        jedis.close();
        return hset;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        String result = jedis.hget(hkey, key);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        Long result = jedis.decr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        Long result = jedis.hdel(hkey,key);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Gj1022");
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }
}
