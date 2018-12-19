package com.daiql.redis;

import com.daiql.redis.bean.Member;
import com.daiql.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class TestRedis {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("testRedis")
    public String TestRedis() {
        redisTemplate.opsForValue().set("study","java");
        System.out.println(redisTemplate.opsForValue().get("study"));
        return (String) redisTemplate.opsForValue().get("study");
    }

    @RequestMapping("testObject")
    public Member TestObject() {
        Member member = new Member();
        member.setAge("30");
        member.setMid("生活不易");
        redisTemplate.opsForValue().set("member",member);
        System.out.println(redisTemplate.opsForValue().get("member"));
        return (Member) redisTemplate.opsForValue().get("member");
    }

    @RequestMapping("testString")
    public String TestString() {
        //判断key是否存在
        System.out.println("判断key999是否存在：" + redisUtil.hasKey("key999"));
        System.out.println("新增key001,value001键值对：" + redisUtil.set("key001","value001"));
        System.out.println("判断key001是否存在：" + redisUtil.hasKey("key001"));

        //输出系统中所有的key
        System.out.println("新增key002,value002的键值对：" + redisUtil.set("key001", "value001"));
        System.out.println("系统中所有的键如下：");
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

        //删除某个key，若key不存在，则忽略该命令
        System.out.println("系统中删除key002：" + redisUtil.del("key002"));
        System.out.println("判断key002是否存在：" + redisUtil.hasKey("key002"));

        //设置 key001的过期时间
        System.out.println("设置key001的过期时间为5秒" + redisUtil.expire("key001",5));
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查看某个key的剩余生存时间，单位【秒】,永久生存或者不存在的都会返回-1
        System.out.println("查看key001的剩余生存时间：" + redisUtil.getExpire("key001"));
        //移除某个key的生成时间
        System.out.println("移除key001的生存时间：" + redisTemplate.persist("key001"));
        System.out.println("查看key001的剩余生存时间：" + redisUtil.getExpire("key001"));
        //查看key所存储的值的类型
        System.out.println("查看key所存储的值得类型：" + redisTemplate.type("key001"));
        System.out.println("查看key001的剩余生存时间：" + redisUtil.getExpire("key001"));
        /**
         * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
         *             2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
         */
        return "请在后台查看打印结果";
    }
}
