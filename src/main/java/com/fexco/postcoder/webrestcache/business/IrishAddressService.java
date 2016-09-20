package com.fexco.postcoder.webrestcache.business;

import com.fexco.postcoder.webrestcache.integration.IrishAddressConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by emival on 20/09/16.
 */
@Service
public class IrishAddressService {

    @Autowired
    private IrishAddressConsumer irishAddressConsumer;

    public String lookupAddress(String addressFragment, Map<String, String> params){
        return irishAddressConsumer.lookupAddress(addressFragment,params);
    }

    public String lookupAddressGeo(String addressFragment, Map<String, String> params){
        return irishAddressConsumer.lookupAddressGeo(addressFragment,params);
    }

    public String lookupAddressCoordinate(String addressFragment, Map<String, String> params){
        return irishAddressConsumer.lookupAddressCoordinate(addressFragment,params);
    }

    public String lookupReverseAddressGeo(String latitude,String longitude, Map<String, String> params){
        return irishAddressConsumer.lookupReverseAddressGeo(latitude,longitude,params);
    }

}
