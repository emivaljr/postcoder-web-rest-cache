package com.fexco.postcoder.webrestcache.business;

import com.fexco.postcoder.webrestcache.integration.UkAddressConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This is a application service implementation that wraps Uk Address calls
 * @author Emival Junior
 * @version 1.0
 */
@Service
public class UkAddressService {

    @Autowired
    private UkAddressConsumer ukAddressConsumer;

    /**
     * Returns full UK addresses for a postcode (e.g. NR14 7PZ)
     * or address fragment (e.g. Fox Road, Framingham Pigot).
     * @param addressFragment postcode or address fragment
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    public String lookupAddress(String addressFragment, Map<String, String> params){
        return ukAddressConsumer.lookupAddress(addressFragment,params);
    }

    /**
     * Returns full UK addresses for a postcode or address fragment including geodata.
     * @param addressFragment postcode or address fragment
     * @param params Optional Request Params Eg. format, lines, include
     * @return Xml or Json String with the lookup result
     */
    public String lookupAddressGeo(String addressFragment, Map<String, String> params){
        return ukAddressConsumer.lookupAddressGeo(addressFragment,params);
    }
}
