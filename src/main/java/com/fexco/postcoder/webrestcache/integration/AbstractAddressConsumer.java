package com.fexco.postcoder.webrestcache.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by emival on 18/09/16.
 */
public abstract class AbstractAddressConsumer {

    @Autowired
    private RestTemplate addressRestTemplate;

    public String exchange(String url, Map<String, String> params) {
        ResponseEntity<String> responseEntity = addressRestTemplate.exchange(url, HttpMethod.GET, null, String.class, params);
        return responseEntity.getBody();
    }
}
