package cn.thc.handleDb.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thc on 2017/1/7
 */
//http://www.cnblogs.com/liuling/p/2014-4-19-04.html
public class TestRedis {
    private Jedis jedis;

    @Before
    public void setup() {
        //连接redis，本地docker中的
        jedis = new Jedis("192.168.31.64", 32775);
//        jedis.auth("admin");
    }

    /**
     * redis存储字符串
     */
    @Test
    public void testString() {
//        //添加数据
//        jedis.set("name","xinxin");
//        System.out.println(jedis.get("name"));
//
//        //拼接
//        jedis.append("name"," is my lover");
//        System.out.println(jedis.get("name"));
//
//        //删除
//        jedis.del("name");
//        System.out.println(jedis.get("name"));

        //设置多个键值对
        jedis.mset("name", "liuliu", "age", "23");
    }

    /**
     * redis操作Map
     */
    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);

        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        //删除map中的某个键值
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user"));//返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value
    }

    /**
     * jedis操作List
     */
    @Test
    public void testList() {
        //开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        //先向key java framework中存放三条数据
        jedis.lpush("java framework", "spring1");
        jedis.lpush("java framework", "spring2");
        jedis.lpush("java framework", "spring3");
        //再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework", 0, -1));

        jedis.del("java framework");
        jedis.rpush("java framework", "spring1");
        jedis.rpush("java framework", "spring2");
        jedis.rpush("java framework", "spring3");
        System.out.println(jedis.lrange("java framework", 0, -1));
    }

    /**
     * jedis操作Set
     */
    @Test
    public void testSet() {
        //添加
        jedis.sadd("user1", "lili1");
        jedis.sadd("user1", "lili2");
        jedis.sadd("user1", "lili3");
        jedis.sadd("user1", "lili4");
        jedis.sadd("user1", "who");

        //移除
        jedis.srem("user1", "who");
        System.out.println(jedis.smembers("user1"));//获取所有加入的value
        System.out.println(jedis.sismember("user1", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("user1"));
        System.out.println(jedis.scard("user1"));//返回集合的元素个数
    }

    /**
     * 排序
     */
    @Test
    public void test() {
        //jedis 排序
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.rpush("a", "6");
        jedis.rpush("a", "3");
        jedis.rpush("a", "9");

        System.out.println(jedis.lrange("a", 0, -1));
        System.out.println(jedis.sort("a"));
        System.out.println(jedis.lrange("a", 0, -1));
    }

    /**
     * 测试连接池
     */
    @Test
    public void testRedisPool() {
        RedisUtil.getJedis().set("newName","中文测试");
        System.out.println(RedisUtil.getJedis().get("newName"));
    }
}


final class RedisUtil {
    //Redis服务器IP
    private static String ADDR = "192.168.31.64";
    //端口号
    private static int PORT = 32775;
    //访问密码
    //private static String AUTH = "admin";
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 10000;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            //如果设置密码，数据库
//            jedisPool = new JedisPool(config,ADDR,PORT,TIMEOUT,AUTH);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放redis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis){
        if (jedis!=null) {
            jedisPool.returnResource(jedis);
        }
    }

}