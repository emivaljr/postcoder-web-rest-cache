package com.fexco.postcoder.webrestcache.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by emival on 17/09/16.
 */
@Component
public class IrishAddressConsumer {

    @Autowired
    private RestTemplate irishAddressRestTemplate;

    @Cacheable(cacheNames = "xml-configured-cache")
    public String lookupIrishAddress(String adressFragment, Map<String, String> params){
        ResponseEntity<String> responseEntity = irishAddressRestTemplate.getForEntity("/address/ie/" + adressFragment, String.class, params);
        return responseEntity.getBody();
    }


}
