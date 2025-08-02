package com.prachimehar.journalApp.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.prachimehar.journalApp.service.JournalEntryService.log;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) {
                log.warn("Key not found in Redis: {}");
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Error retrieving value from Redis for key: {}", e);
            return null;
        }
    }


    public void set(String key,Object o,Long ttl)  {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
        }catch(Exception e){
            log.error("Exception",e);
        }
    }
}
