package com.fexco.postcoder.webrestcache.integration;

import com.fexco.postcoder.webrestcache.infra.util.RestUrlBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by emival on 17/09/16.
 */
@Component
public class IrishAddressConsumer  extends AbstractAddressConsumer{

    @Cacheable(cacheNames = "lookup-irish-address")
    public String lookupAddress(String addressFragment, Map<String, String> params){
        String url = new RestUrlBuilder()
                    .prefix("/address/ie/")
                    .addPathParam(addressFragment)
                    .urlVariables(params)
                    .build();
        return exchange(url, params);
    }

    @Cacheable(cacheNames = "lookup-irish-address-geo")
    public String lookupAddressGeo(String addressFragment, Map<String, String> params){
        String url = new RestUrlBuilder()
                .prefix("/addressgeo/ie/")
                .addPathParam(addressFragment)
                .urlVariables(params)
                .build();
        return exchange(url, params);
    }



}
