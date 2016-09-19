package com.fexco.postcoder.webrestcache;

import com.fexco.postcoder.webrestcache.infra.errors.RestResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by emival on 17/09/16.
 */
@Configuration
@EnableCaching
public class AppConfiguration {

    @Value(value = "${postcoder.web.api.key}")
    private String apiKey;

    @Autowired
    private RestResponseEntityExceptionHandler restTemplateErrorHandler;

    @Bean(name = "addressRestTemplate")
    public RestTemplate buildAddressRestTemplate(){
        return new RestTemplateBuilder()
                .rootUri("http://ws.postcoder.com/pcw/"+apiKey)
                .build();
    }

}
