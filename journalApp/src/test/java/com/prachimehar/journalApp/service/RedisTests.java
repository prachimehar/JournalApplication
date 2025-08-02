package com.prachimehar.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    public RedisTemplate redisTemplate;

    @Disabled
    @Test
    void sendMail() throws InterruptedException {
        redisTemplate.opsForValue().set("email","prachimehar841@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        System.out.println("Email from Redis: " + email);


    }
}
