package com.fexco.postcoder.webrestcache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

/**
 * This class holds any application configuration including annotation for enable
 * aspectJ and caching.
 * @author Emival Junior
 * @version 1.0
 */
@Configuration
@EnableAspectJAutoProxy
@EnableCaching
public class AppConfiguration {

    /**
     * PostCoder Web API Key.
     */
    @Value(value = "${postcoder.web.api.key}")
    private String apiKey;


    /**
     * Build default RestTemplate implementation
     * @return RestTemplate configured for PostCoder Web API
     */
    @Bean(name = "addressRestTemplate")
    public RestTemplate buildAddressRestTemplate(){
        return new RestTemplateBuilder()
                .rootUri("http://ws.postcoder.com/pcw/"+apiKey)
                .build();
    }

}
