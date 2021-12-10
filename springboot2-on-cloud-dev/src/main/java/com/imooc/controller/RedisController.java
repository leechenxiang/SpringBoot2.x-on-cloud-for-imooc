package com.imooc.controller;

import com.imooc.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("redis")
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisOperator redis;

    @GetMapping("create")
    public String create(String k, String v){
        redisTemplate.opsForValue().set(k, v);
        return "OK~";
    }

    @GetMapping("get")
    public Object get(String k){
        return redisTemplate.opsForValue().get(k);
    }

    @GetMapping("update")
    public String update(String k, String v){
        redisTemplate.opsForValue().set(k, v);
        return "OK";
    }

    @GetMapping("delete")
    public String delete(String k){
        redisTemplate.delete(k);
        return "OK";
    }
}
