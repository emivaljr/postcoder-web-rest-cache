package com.fexco.postcoder.webrestcache.business;

import com.fexco.postcoder.webrestcache.integration.IrishAddressConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This is a application service implementation that wraps Irish Address calls
 * @author Emival Junior
 * @version 1.0
 */
@Service
public class IrishAddressService {

    @Autowired
    private IrishAddressConsumer irishAddressConsumer;

    /**
     * Perform an address lookup using an Eircode or address fragment.
     * @param addressFragment Eircode or address fragment
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    public String lookupAddress(String addressFragment, Map<String, String> params){
        return irishAddressConsumer.lookupAddress(addressFragment,params);
    }

    /**
     * Perform an address and coordinate lookup using an Eircode or address fragment.
     * @param addressFragment Eircode or address fragment
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    public String lookupAddressGeo(String addressFragment, Map<String, String> params){
        return irishAddressConsumer.lookupAddressGeo(addressFragment,params);
    }

    /**
     * Perform a coordinate lookup using an Eircode.
     * @param eircode Eircode
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    public String lookupAddressCoordinate(String eircode, Map<String, String> params){
        return irishAddressConsumer.lookupAddressCoordinate(eircode,params);
    }

    /**
     * Return an address from its latitude and longitude (e.g. 53.332067,-6.255492)
     * @param latitude Latitude
     * @param longitude Longitude
     * @param params Optional Request Params Eg. distance, format, lines, include
     * @return Xml or Json String with the lookup result
     */
    public String lookupReverseAddressGeo(String latitude,String longitude, Map<String, String> params){
        return irishAddressConsumer.lookupReverseAddressGeo(latitude,longitude,params);
    }

}
