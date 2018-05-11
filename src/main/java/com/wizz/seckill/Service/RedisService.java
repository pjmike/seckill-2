package com.wizz.seckill.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisService {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    public void setCnt(String key, Long val){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
         entityIdCounter.set(val);
    }

    public Long incrCnt(String key){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return entityIdCounter.getAndIncrement();
    }

    public Long increment(String key){
        return redisTemplate.opsForValue().increment(key,1);
    }

    public Long get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
       redisTemplate.opsForValue().getOperations().delete(key);
    }
}