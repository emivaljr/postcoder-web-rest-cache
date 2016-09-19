package com.fexco.postcoder.webrestcache.rest;

import com.fexco.postcoder.webrestcache.integration.IrishAddressConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * Created by emival on 18/09/16.
 */
@RestController
public class IrishAddressProvider {

    @Autowired
    private IrishAddressConsumer irishAddressConsumer;

    @RequestMapping(value = {"/address/ie/{address}","/address/ie/"})
    @ResponseBody
    public String lookupAddress(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return irishAddressConsumer.lookupAddress(address.isPresent()?address.get():"",allRequestParams);
    }

    @RequestMapping(value = {"/addressgeo/ie/{address}","/addressgeo/ie/"})
    @ResponseBody
    public String lookupAddressGeo(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return irishAddressConsumer.lookupAddressGeo(address.isPresent()?address.get():"",allRequestParams);
    }
}
