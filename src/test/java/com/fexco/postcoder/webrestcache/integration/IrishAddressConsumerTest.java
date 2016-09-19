package com.fexco.postcoder.webrestcache.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.*;

/**
 * Created by emival on 17/09/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IrishAddressConsumerTest {

    @MockBean
    private RestTemplate irishAddressRestTemplate;

    @Autowired
    private IrishAddressConsumer irishAddressConsumer;

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

    @Test
    public void testLookupIrishAddress() {
        given(this.irishAddressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap())).willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = irishAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }

    @Test
    public void testLookupIrishAddressInvalidKey() {
        given(this.irishAddressRestTemplate.getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        try {
            String response = irishAddressConsumer.lookupAddress("D02X285", params);
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof HttpClientErrorException);
        }
    }

    @Test
    public void testLookupIrishAddressCache() {
        //First time to fill the cache.
        given(this.irishAddressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = irishAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Second time should not call the RestTemplate class and return the same result as previous call
        given(this.irishAddressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(null);
        response = irishAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Third time with different params should call the RestTemplate class.
        given(this.irishAddressRestTemplate
                .getForEntity(contains("D02A285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCacheAndNewValue", HttpStatus.OK));
        response = irishAddressConsumer.lookupAddress("D02A285", params);
        Assert.assertEquals(response, "testWithCacheAndNewValue");
    }

}
