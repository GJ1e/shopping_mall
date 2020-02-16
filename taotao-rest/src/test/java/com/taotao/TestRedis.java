package com.taotao;

import com.taotao.rest.component.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * @Author GJ1e
 * @Create 2020/2/11
 * @Time 21:21
 */
public class TestRedis {

//    @Test
//    public void testJedis(){
//        Jedis jedis = new Jedis("123.56.239.240",6379);
//        jedis.auth("Gj1022");
//        jedis.set("key1","123");
//        String string = jedis.get("key1");
//        System.out.println(string);
//        jedis.close();
//
//
//    }
//
//    @Test
//    public void test2(){
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
//        jedisClient.set("client1","1000");
//        String string  = jedisClient.get("client1");
//        System.out.println(string);
//
//
//    }
}
