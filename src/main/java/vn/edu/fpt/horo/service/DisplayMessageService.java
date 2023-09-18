package vn.edu.fpt.horo.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * vn.edu.fpt.accounts.service
 **/

@CacheConfig(cacheNames = "displayMessage")
public interface DisplayMessageService {
    @Cacheable
    String getDisplayMessage(String code);

    @Cacheable
    String getDisplayMessage(String code, String language);

}

