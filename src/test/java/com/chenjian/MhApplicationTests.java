package com.chenjian;

import com.chenjian.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MhApplicationTests {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads() {

        redisTemplate.opsForHash().put("变异地精战士","内容","是等发达所发生的法师");
        System.out.println(redisTemplate.opsForHash().get("变异地精战士","内容"));
    }

    @Test
    public void dd(){
        redisUtil.hset("变异地精战士","内容","撒打发斯蒂芬法师");
        System.out.println(redisTemplate.opsForHash().get("变异地精战士","内容"));
    }

}
