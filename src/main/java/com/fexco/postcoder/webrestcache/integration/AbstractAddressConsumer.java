package com.fexco.postcoder.webrestcache.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * This is a Abstraction of Consumer implementation that include commom implementation of
 * Address Consumers.
 * @author Emival Junior
 * @version 1.0
 */
public abstract class AbstractAddressConsumer {

    @Autowired
    private RestTemplate addressRestTemplate;

    /**
     * This method wraps exchange call of Spring RestTemplate method.
     * @param url URL
     * @param params exchange call request params
     * @return result of the RestTemplate Call.
     */
    protected String exchange(String url, Map<String, String> params) {
        ResponseEntity<String> responseEntity = addressRestTemplate.getForEntity(url, String.class, params);
        return responseEntity.getBody();
    }
}
