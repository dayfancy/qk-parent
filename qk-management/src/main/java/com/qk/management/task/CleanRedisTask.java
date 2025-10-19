package com.qk.management.task;

import com.qk.common.constant.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author: RightSquare
 * @Date: 2025/10/19 16:06
 * @Description:
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class CleanRedisTask {
    @Autowired
    private StringRedisTemplate redisClient;

    @Scheduled(cron = "0 0 12 ? * ? ")
    public void cleanRedis() {
        try {
            Set<String> keys = redisClient.keys(CacheConstants.CACHE_PORTAL_KEY_PREFIX + "*");
            if (keys != null && !keys.isEmpty()) {
                redisClient.delete(keys);
                log.info("清理缓存成功，共清理 {} 个键", keys.size());
                System.out.println("清理缓存成功");
            }
        } catch (Exception e) {
            log.error("清理缓存失败", e);
        }
    }
}
