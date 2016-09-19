package com.fexco.postcoder.webrestcache;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * Created by emival on 19/09/16.
 */
public class AbstractTest {

    @Autowired
    private CacheManager cacheManager;

    /**
     * Cleaning Spring cache before each execution
     */
    @Before
    public void init() {
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);
            cache.clear();
        }
    }
}
