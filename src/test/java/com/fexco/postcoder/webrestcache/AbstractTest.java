package com.fexco.postcoder.webrestcache;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * Abstract Test class for common test code implementation.
 * @author Emival Junior
 * @version 1.0
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
