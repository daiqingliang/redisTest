package com.daiql.redis;

import com.daiql.redis.bean.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedis {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("testString")
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


}
