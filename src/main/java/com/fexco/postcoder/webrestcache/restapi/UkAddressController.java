package com.fexco.postcoder.webrestcache.restapi;

import com.fexco.postcoder.webrestcache.business.UkAddressService;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * This is a Rest Front Controller implementation that receives external calls from clients of
 * this app and redirect them to the corresponding service.
 * This controller was created specifically for Uk Address calls.
 * @author Emival Junior
 * @version 1.0
 */
@RestController
public class UkAddressController {

    @Autowired
    private UkAddressService ukAddressService;

    /**
     * Receive calls for /address/uk/ path
     * @param address address of the search
     * @param allRequestParams optional request params of the search
     * @return result of the search
     */
    @RequestMapping(value = {UrlPrefixConstants.UK_ADDRESS_LOOKUP+"{address}",UrlPrefixConstants.UK_ADDRESS_LOOKUP})
    public @ResponseBody
    String lookupAddress(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return ukAddressService.lookupAddress(address.isPresent()?address.get():"",allRequestParams);
    }

    /**
     * Receive calls for /addressgeo/uk/ path
     * @param address address of the search
     * @param allRequestParams optional request params of the search
     * @return result of the search
     */
    @RequestMapping(value = {UrlPrefixConstants.UK_ADDRESS_GEO_LOOKUP+"{address}",UrlPrefixConstants.UK_ADDRESS_GEO_LOOKUP})
    public @ResponseBody String lookupAddressGeo(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return ukAddressService.lookupAddressGeo(address.isPresent()?address.get():"",allRequestParams);
    }
}
