package com.fexco.postcoder.webrestcache.integration;

import com.fexco.postcoder.webrestcache.infra.builders.RestUrlBuilder;
import com.fexco.postcoder.webrestcache.infra.constants.CacheNamesConstants;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by emival on 19/09/16.
 */
@Component
public class UkAddressConsumer extends AbstractAddressConsumer{

    @Cacheable(cacheNames = CacheNamesConstants.UK_ADDRESS_LOOKUP)
    public String lookupAddress(String addressFragment, Map<String, String> params){
        String url = new RestUrlBuilder()
                .prefix(UrlPrefixConstants.UK_ADDRESS_LOOKUP)
                .addPathParam(addressFragment)
                .urlVariables(params)
                .build();
        return exchange(url, params);
    }

    @Cacheable(cacheNames = CacheNamesConstants.UK_ADDRESS_GEO_LOOKUP)
    public String lookupAddressGeo(String addressFragment, Map<String, String> params){
        String url = new RestUrlBuilder()
                .prefix(UrlPrefixConstants.UK_ADDRESS_GEO_LOOKUP)
                .addPathParam(addressFragment)
                .urlVariables(params)
                .build();
        return exchange(url, params);
    }
}
