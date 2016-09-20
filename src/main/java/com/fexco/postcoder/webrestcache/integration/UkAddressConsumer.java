package com.fexco.postcoder.webrestcache.integration;

import com.fexco.postcoder.webrestcache.infra.builders.RestUrlBuilder;
import com.fexco.postcoder.webrestcache.infra.constants.CacheNamesConstants;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This is a Consumer implementation that executes calls for Postcoder Web API,
 * specifically for UK Address API part.
 * In order to minimize the costs of calling this API, repeated calls are cached
 * using Spring Cache and Infinispan libraries.
 * @author Emival Junior
 * @version 1.0
 */
@Component
public class UkAddressConsumer extends AbstractAddressConsumer{

    /**
     * Returns full UK addresses for a postcode or address fragment.
     * This method is executed only if the result isn't on Spring Cache.
     * @param addressFragment postcode or address fragment
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    @Cacheable(cacheNames = CacheNamesConstants.UK_ADDRESS_LOOKUP)
    public String lookupAddress(String addressFragment, Map<String, String> params){
        String url = new RestUrlBuilder()
                .prefix(UrlPrefixConstants.UK_ADDRESS_LOOKUP)
                .addPathParam(addressFragment)
                .urlVariables(params)
                .build();
        return exchange(url, params);
    }

    /**
     * Returns full UK addresses for a postcode or address fragment including geodata.
     * This method is executed only if the result isn't on Spring Cache.
     * @param addressFragment postcode or address fragment
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
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
