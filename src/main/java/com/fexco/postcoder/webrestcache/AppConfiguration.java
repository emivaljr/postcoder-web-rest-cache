package com.fexco.postcoder.webrestcache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by emival on 17/09/16.
 */
@Configuration
@EnableCaching
public class AppConfiguration {

    @Value(value = "${postcoder.web.api.key}")
    private String apiKey;

    @Bean(name = "irishAddressRestTemplate")
    public RestTemplate buildIrishAddressRestTemplate(){
        return new RestTemplateBuilder()
                .rootUri("http://ws.postcoder.com/pcw/"+apiKey)
                .build();
    }

}
