package com.lzk;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    /**
     * jedis.clients.jedis.exceptions.JedisConnectionException: java.net.SocketTimeoutE
     * 原因：未打开6379的端口
     * 解决方式：
     *  1.打下防火墙设置，修改配置
     *      vim /etc/sysconfig/iptables
     *  2.打开6379的端口
     *      -A INPUT -m state --state NEW -m tcp -p tcp --dport 6379 -j ACCEPT
     *  3.保存退出
     *  4.重启防火墙设置
     *      service iptables restart
     */
    @Test
    public void jedisTest(){
        //1、设置地址和端口
        Jedis jedis = new Jedis("192.168.0.111",6379);
        //2、保存数据
        jedis.set("name1","李四");
        //3、获取数据
        String value = jedis.get("name1");
        System.out.println(value);
        //4、释放资源
        jedis.close();
    }

    @Test
    public void jedisPoolTest(){
        //获得连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(30);
        //设置最大空闲数
        config.setMaxIdle(10);
        //获得连接池
        JedisPool jedisPool = new JedisPool(config,"192.168.0.111",6379);

        //获得核心对象
        Jedis jedis = null;
        try {
            //通过连接池获得连接
            jedis = jedisPool.getResource();
            //设置数据
            jedis.set("name","张三");
            //获取数据
            String value = jedis.get("name");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (jedis != null){
                jedis.close();
            }
            if (jedisPool != null){
                jedisPool.close();
            }
        }
    }
}
