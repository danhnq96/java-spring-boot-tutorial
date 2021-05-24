package com.csf.whoami.config;

import java.util.concurrent.TimeUnit;

import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CacheManager cacheManager() {
        /*return new SpringCache2kCacheManager().defaultSetup(b->b.entryCapacity(2000))
                .addCaches(b->b.name("token").expireAfterWrite(6, TimeUnit.HOURS).entryCapacity(30000));*/

        SpringCache2kCacheManager cacheManager = new SpringCache2kCacheManager();
        if (cacheManager.getCacheNames().stream().filter(name -> name.equals("token")).count() == 0) {
            cacheManager.addCaches(b -> b.name("token").expireAfterWrite(6, TimeUnit.HOURS).entryCapacity(30000));
        }
        return cacheManager;

    }
}
