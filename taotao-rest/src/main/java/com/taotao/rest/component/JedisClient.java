package com.taotao.rest.component;


/**
 * @Author GJ1e
 * @Create 2020/2/11
 * @Time 21:50
 */
public interface JedisClient {
    String set(String key, String value);
    String get(String key);
    Long hset(String hkey, String key, String value);
    String hget(String hkey, String key);
    Long incr(String key);
    Long decr(String key);
    Long expire(String key, int second);
    Long ttl(String key);
    Long hdel(String hkey,String key);
    Long del(String key);
}
