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
 * Test class of Irish Address Consumer
 * @author Emival Junior
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IrishAddressConsumerTest extends AbstractTest{

    @MockBean
    private RestTemplate addressRestTemplate;

    @Autowired
    private IrishAddressConsumer irishAddressConsumer;


    @Test
    public void testLookupAddress() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap())).willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = irishAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }
    @Test
    public void testLookupAddressEmptyParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        String response = irishAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }
    @Test
    public void testLookupAddressNullParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        String response = irishAddressConsumer.lookupAddress("D02X285", null);
        Assert.assertEquals(response, "testWithoutCache");
    }

    @Test(expected = HttpClientErrorException.class)
    public void testLookupAddressInvalidKey() {
        given(this.addressRestTemplate.getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = irishAddressConsumer.lookupAddress("D02X285", params);
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
        String response = irishAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Second time should not call the RestTemplate class and return the same result as previous call
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(null);
        response = irishAddressConsumer.lookupAddress("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Third time with different params should call the RestTemplate class.
        given(this.addressRestTemplate
                .getForEntity(contains("D02A285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCacheAndNewValue", HttpStatus.OK));
        response = irishAddressConsumer.lookupAddress("D02A285", params);
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
        String response = irishAddressConsumer.lookupAddressGeo("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }

    @Test(expected = HttpClientErrorException.class)
    public void testLookupAddressGeoInvalidKey() {
        given(this.addressRestTemplate.getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = irishAddressConsumer.lookupAddressGeo("D02X285", params);
    }

    @Test
    public void testLookupAddressGeoAddressEmptyParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        String response = irishAddressConsumer.lookupAddressGeo("D02X285", params);
        Assert.assertEquals(response, "testWithoutCache");
    }
    @Test
    public void testLookupAddressGeoAddressNullParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        String response = irishAddressConsumer.lookupAddressGeo("D02X285", null);
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
        String response = irishAddressConsumer.lookupAddressGeo("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Second time should not call the RestTemplate class and return the same result as previous call
        given(this.addressRestTemplate
                .getForEntity(contains("D02X285"), eq(String.class), anyMap()))
                .willReturn(null);
        response = irishAddressConsumer.lookupAddressGeo("D02X285", params);
        Assert.assertEquals(response, "testWithCache");
        //Third time with different params should call the RestTemplate class.
        given(this.addressRestTemplate
                .getForEntity(contains("D02A285"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCacheAndNewValue", HttpStatus.OK));
        response = irishAddressConsumer.lookupAddressGeo("D02A285", params);
        Assert.assertEquals(response, "testWithCacheAndNewValue");
    }

    @Test
    public void testLookupReverseAddressGeo() {
        given(this.addressRestTemplate
                .getForEntity(contains("53.332067"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = irishAddressConsumer.lookupReverseAddressGeo("53.332067","-6.255492", params);
        Assert.assertEquals(response, "testWithoutCache");
    }
    @Test(expected = HttpClientErrorException.class)
    public void testLookupReverseAddressGeoInvalidKey() {
        given(this.addressRestTemplate.getForEntity(contains("53.332067"), eq(String.class), anyMap()))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = irishAddressConsumer.lookupReverseAddressGeo("53.332067","-6.255492", params);

    }
    @Test
    public void testLookupReverseAddressGeoEmptyParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("53.332067"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        String response = irishAddressConsumer.lookupReverseAddressGeo("53.332067","-6.255492", params);
        Assert.assertEquals(response, "testWithoutCache");
    }

    @Test
    public void testLookupReverseAddressGeoNullParams() {
        given(this.addressRestTemplate
                .getForEntity(contains("53.332067"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithoutCache", HttpStatus.OK));
        String response = irishAddressConsumer.lookupReverseAddressGeo("53.332067","-6.255492", null);
        Assert.assertEquals(response, "testWithoutCache");
    }

    @Test
    public void testLookupReverseAddressGeoCache() {
        //First time to fill the cache.
        given(this.addressRestTemplate
                .getForEntity(contains("53.332067"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCache", HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines", "3");
        params.put("format", "json");
        String response = irishAddressConsumer.lookupReverseAddressGeo("53.332067","-6.255492", null);
        Assert.assertEquals(response, "testWithCache");
        //Second time should not call the RestTemplate class and return the same result as previous call
        given(this.addressRestTemplate
                .getForEntity(contains("53.332067"), eq(String.class), anyMap()))
                .willReturn(null);
        response = irishAddressConsumer.lookupReverseAddressGeo("53.332067","-6.255492", null);
        Assert.assertEquals(response, "testWithCache");
        //Third time with different params should call the RestTemplate class.
        given(this.addressRestTemplate
                .getForEntity(contains("54.332067"), eq(String.class), anyMap()))
                .willReturn(new ResponseEntity<String>("testWithCacheAndNewValue", HttpStatus.OK));
        response = irishAddressConsumer.lookupReverseAddressGeo("54.332067","-6.255492", null);
        Assert.assertEquals(response, "testWithCacheAndNewValue");
    }



}
