package com.fexco.postcoder.webrestcache.business;

import com.fexco.postcoder.webrestcache.integration.UkAddressConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by emival on 20/09/16.
 */
@Service
public class UkAddressService {

    @Autowired
    private UkAddressConsumer ukAddressConsumer;

    public String lookupAddress(String addressFragment, Map<String, String> params){
        return ukAddressConsumer.lookupAddress(addressFragment,params);
    }

    public String lookupAddressGeo(String addressFragment, Map<String, String> params){
        return ukAddressConsumer.lookupAddressGeo(addressFragment,params);
    }
}
