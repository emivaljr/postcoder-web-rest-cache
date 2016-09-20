package com.fexco.postcoder.webrestcache.restapi;

import com.fexco.postcoder.webrestcache.business.IrishAddressService;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * Created by emival on 18/09/16.
 */
@RestController
public class IrishAddressController {

    @Autowired
    private IrishAddressService irishAddressService;

    @RequestMapping(value = {UrlPrefixConstants.IRISH_ADDRESS_LOOKUP+"{address}",UrlPrefixConstants.IRISH_ADDRESS_LOOKUP})
    public @ResponseBody String lookupAddress(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return irishAddressService.lookupAddress(address.isPresent()?address.get():"",allRequestParams);
    }

    @RequestMapping(value = {UrlPrefixConstants.IRISH_ADDRESS_GEO_LOOKUP+"{address}",UrlPrefixConstants.IRISH_ADDRESS_GEO_LOOKUP})
    public @ResponseBody String lookupAddressGeo(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return irishAddressService.lookupAddressGeo(address.isPresent()?address.get():"",allRequestParams);
    }
    @RequestMapping(value = {UrlPrefixConstants.IRISH_ADDRESS_COORDINATE_LOOKUP+"{address}",UrlPrefixConstants.IRISH_ADDRESS_COORDINATE_LOOKUP})
    public @ResponseBody String lookupAddressCoordinate(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return irishAddressService.lookupAddressCoordinate(address.isPresent()?address.get():"",allRequestParams);
    }

    @RequestMapping(value = {UrlPrefixConstants.IRISH_REVERSE_ADDRESS_GEO_LOOKUP+"{latitude}/{longitude:.+}",UrlPrefixConstants.IRISH_REVERSE_ADDRESS_GEO_LOOKUP})
    public @ResponseBody String lookupReverseAddressGeo(@PathVariable Optional<String> latitude,@PathVariable Optional<String> longitude, @RequestParam Map<String,String> allRequestParams){
        return irishAddressService.lookupReverseAddressGeo(
                latitude.isPresent()?latitude.get():"",
                longitude.isPresent()?longitude.get():"",
                allRequestParams);
    }
}
