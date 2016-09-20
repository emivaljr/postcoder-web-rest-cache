package com.fexco.postcoder.webrestcache.restapi;

import com.fexco.postcoder.webrestcache.business.UkAddressService;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * Created by emival on 19/09/16.
 */
@RestController
public class UkAddressController {

    @Autowired
    private UkAddressService ukAddressService;

    @RequestMapping(value = {UrlPrefixConstants.UK_ADDRESS_LOOKUP+"{address}",UrlPrefixConstants.UK_ADDRESS_LOOKUP})
    public @ResponseBody
    String lookupAddress(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return ukAddressService.lookupAddress(address.isPresent()?address.get():"",allRequestParams);
    }

    @RequestMapping(value = {UrlPrefixConstants.UK_ADDRESS_GEO_LOOKUP+"{address}",UrlPrefixConstants.UK_ADDRESS_GEO_LOOKUP})
    public @ResponseBody String lookupAddressGeo(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return ukAddressService.lookupAddressGeo(address.isPresent()?address.get():"",allRequestParams);
    }
}
