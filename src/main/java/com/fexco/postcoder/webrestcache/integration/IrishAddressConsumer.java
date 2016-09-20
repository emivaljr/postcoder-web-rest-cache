package com.fexco.postcoder.webrestcache.integration;

import com.fexco.postcoder.webrestcache.infra.builders.RestUrlBuilder;
import com.fexco.postcoder.webrestcache.infra.constants.CacheNamesConstants;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This is a Consumer implementation that executes calls for Postcoder Web API,
 * specifically for Irish Address API part.
 * In order to minimize the costs of calling this API, repeated calls are cached
 * using Spring Cache and Infinispan libraries.
 * @author Emival Junior
 * @version 1.0
 */
@Component
public class IrishAddressConsumer  extends AbstractAddressConsumer{

    /**
     * Perform an address lookup using an Eircode or address fragment.
     * This method is executed only if the result isn't on Spring Cache.
     * @param addressFragment Eircode or address fragment
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    @Cacheable(cacheNames = CacheNamesConstants.IRISH_ADDRESS_LOOKUP)
    public String lookupAddress(String addressFragment, Map<String, String> params){
        String url = new RestUrlBuilder()
                    .prefix(UrlPrefixConstants.IRISH_ADDRESS_LOOKUP)
                    .addPathParam(addressFragment)
                    .urlVariables(params)
                    .build();
        return exchange(url, params);
    }

    /**
     * Perform an address and coordinate lookup using an Eircode or address fragment.
     * This method is executed only if the result isn't on Spring Cache.
     * @param addressFragment Eircode or address fragment
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    @Cacheable(cacheNames = CacheNamesConstants.IRISH_ADDRESS_GEO_LOOKUP)
    public String lookupAddressGeo(String addressFragment, Map<String, String> params){
        String url = new RestUrlBuilder()
                .prefix(UrlPrefixConstants.IRISH_ADDRESS_GEO_LOOKUP)
                .addPathParam(addressFragment)
                .urlVariables(params)
                .build();
        return exchange(url, params);
    }

    /**
     * Perform a coordinate lookup using an Eircode.
     * This method is executed only if the result isn't on Spring Cache.
     * @param eircode Eircode
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    @Cacheable(cacheNames = CacheNamesConstants.IRISH_ADDRESS_COORDINATE_LOOKUP)
    public String lookupAddressCoordinate(String eircode, Map<String, String> params){
        String url = new RestUrlBuilder()
                .prefix(UrlPrefixConstants.IRISH_ADDRESS_COORDINATE_LOOKUP)
                .addPathParam(eircode)
                .urlVariables(params)
                .build();
        return exchange(url, params);
    }

    /**
     * Return an address from its latitude and longitude (e.g. 53.332067,-6.255492)
     * This method is executed only if the result isn't on Spring Cache.
     * @param latitude Latitude
     * @param longitude Longitude
     * @param params Optional Request Params Eg. distance, format, lines, include
     * @return Xml or Json String with the lookup result
     */
    @Cacheable(cacheNames = CacheNamesConstants.IRISH_REVERSE_ADDRESS_GEO_LOOKUP)
    public String lookupReverseAddressGeo(String latitude,String longitude, Map<String, String> params){
        String url = new RestUrlBuilder()
                .prefix(UrlPrefixConstants.IRISH_REVERSE_ADDRESS_GEO_LOOKUP)
                .addPathParam(latitude)
                .addPathParam(longitude)
                .urlVariables(params)
                .build();
        return exchange(url, params);
    }



}
