package com.cjc.movie.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/9 15:13
 */

@Component
public class RedisUtil {
    @Autowired
    private static RedisTemplate redisTemplate;

    public static void setKeyVal(String key, Object value){
        redisTemplate.opsForValue().set(key,value);
    }
    public static Object getStringByKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

