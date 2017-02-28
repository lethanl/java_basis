package cn.thc.handleDb.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by thc on 2017/1/7
 */
public class RedisJava {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.31.64",32775);
        System.out.println("success");
        System.out.println(jedis.ping());

        //Redis Java String(字符串) 实例
//        jedis.set("test","hello world!");
//        System.out.println(jedis.get("test"));

        //Redis Java List(列表) 实例
//        jedis.lpush("test-list","test1");
//        jedis.lpush("test-list","test2");
//        jedis.lpush("test-list","test3");
//        List<String> list = jedis.lrange("test-list",0,10);
//        for (String s : list) {
//            System.out.println(s);
//        }

    }
}
