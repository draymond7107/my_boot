package com.draymond.my_boot.cache;

import com.draymond.commons.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/5 19:22
 */
@Component
public class BaseCache {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public BaseCache() {
    }


    public <T> void putString(String key, T obj) {
        stringRedisTemplate.opsForValue().set(key, JsonUtils.toString(obj));
    }

    public <T> void putString(String key, T obj, int timeout) {
        this.putString(key, obj, timeout, TimeUnit.MINUTES);
    }

    public <T> void putString(String key, T obj, int timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JsonUtils.toString(obj), (long) timeout, unit);
    }

    public <T> String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    // -------------  通用方法  ---------------------
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 模糊匹配删除
     *
     * @param key
     */
    public void delStartWithLike(String key) {
        Set<String> keys = redisTemplate.keys(key.concat("*"));
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    public boolean expire(String key, int timeout) {
        return this.expire(key, timeout, TimeUnit.MINUTES);
    }

    public boolean expire(String key, int timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }


}