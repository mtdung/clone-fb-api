package vn.edu.fpt.fb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;
import vn.edu.fpt.fb.exception.BusinessException;
import vn.edu.fpt.fb.utils.Utils;

import java.util.concurrent.TimeUnit;

import static vn.edu.fpt.fb.common.constant.Constant.DEFAULT_REDIS_EXPIRED;

/**
 * @author namlh4
 */
@Service
@Slf4j
public class RedisService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error("Failed to set key {} value {}", key, value, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            log.error("Failed to hasKey key {}", key, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    //timeout is seconds
    public boolean set(String key, String value, int timeout) {
        if (timeout == 0) {
            timeout = DEFAULT_REDIS_EXPIRED;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("Failed to set key {} value {} ex {}", key, value, timeout, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public String get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("Failed to get key {}", key, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    //return by seconds

    public long getEx(String key) {
        try {
            if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
                return redisTemplate.getExpire(key, TimeUnit.SECONDS);
            }
            return 0;
        } catch (Exception e) {
            log.error("Failed to getEx key {}", key, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public boolean setEx(String key, long timeout) {
        try {
            if (timeout == 0) {
                timeout = DEFAULT_REDIS_EXPIRED;
            }
            if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
                return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, TimeUnit.SECONDS));
            }
            return false;
        } catch (Exception e) {
            log.error("Failed to getEx key {}", key, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public long increment(String key) {
        try {
            return redisTemplate.opsForValue().increment(key);
        } catch (Exception e) {
            log.error("Failed to increment key {}", key, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public long decrement(String key) {
        try {
            return redisTemplate.opsForValue().decrement(key);
        } catch (Exception e) {
            log.error("Failed to decrement key {}", key, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void delete(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("Failed to decrement key {}", key, e);
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
