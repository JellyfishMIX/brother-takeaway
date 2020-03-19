package me.jmix.brothertakeaway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 当前时间 + 超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        // 此处redisTemplate.opsForValue().setIfAbsent(key, value)使用了redis官方文档的SETNX方法
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        // 如果出现线程阻塞，为防止死锁，需要设置锁有效期机制

        String currentValue = redisTemplate.opsForValue().get(key);
        // 如果锁过期
        if (!StringUtils.isEmpty(currentValue) && (Long.parseLong(currentValue) < System.currentTimeMillis())) {
            // 获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            // 如果有两个线程获取到了currentValue = A，这两个线程的value都是B，其中一个线程拿到锁
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 解锁，即删除key
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常，errMsg = {}", e.getMessage());
        }
    }
}