package com.fexco.postcoder.webrestcache.integration;

import com.fexco.postcoder.webrestcache.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class UkAddressConsumerTest extends AbstractTest{

    @MockBean
    private RestTemplate addressRestTemplate;

    @Autowired
    private UkAddressConsumer ukAddressConsumer;


    @Test
    public void testLookupAddress() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap())).willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = ukAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }
    @Test
    public void testLookupAddressEmptyParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        String response = ukAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }
    @Test
    public void testLookupAddressNullParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        String response = ukAddressConsumer.lookupAddress("D02X285", null);
        Assert.assertEquals(response, "testWithoutCache");
    }

    @Test(expected = HttpClientErrorException.class)
    public void testLookupAddressInvalidKey() {
        given(this.addressRestTemplate.getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = ukAddressConsumer.lookupAddress("D02X285", params);
    }

    @Test
    public void testLookupAddressCache() {
        //First time to fill the cache.
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = ukAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Second time should not call the RestTemplate class and return the same result as previous call
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(null);
        response = ukAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Third time with different params should call the RestTemplate class.
        given(this.addressRestTemplate
                .getForEntity(contains("D02A285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCacheAndNewValue", HttpStatus.OK));
        response = ukAddressConsumer.lookupAddress("D02A285", params);
        Assert.assertEquals(response, "testWithCacheAndNewValue");
    }

    @Test
    public void testLookupAddressGeoAddress() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = ukAddressConsumer.lookupAddressGeo("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }

    @Test(expected = HttpClientErrorException.class)
    public void testLookupAddressGeoInvalidKey() {
        given(this.addressRestTemplate.getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = ukAddressConsumer.lookupAddressGeo("D02X285", params);
    }

    @Test
    public void testLookupAddressGeoAddressEmptyParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        String response = ukAddressConsumer.lookupAddressGeo("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }
    @Test
    public void testLookupAddressGeoAddressNullParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        String response = ukAddressConsumer.lookupAddressGeo("D02X285", null);
        Assert.assertEquals(response, "testWithoutCache");
    }

    @Test
    public void testLookupAddressGeoCache() {
        //First time to fill the cache.
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = ukAddressConsumer.lookupAddressGeo("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Second time should not call the RestTemplate class and return the same result as previous call
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(null);
        response = ukAddressConsumer.lookupAddressGeo("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Third time with different params should call the RestTemplate class.
        given(this.addressRestTemplate
                .getForEntity(contains("D02A285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCacheAndNewValue", HttpStatus.OK));
        response = ukAddressConsumer.lookupAddressGeo("D02A285", params);
        Assert.assertEquals(response, "testWithCacheAndNewValue");
    }







}
