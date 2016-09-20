package com.fexco.postcoder.webrestcache.restapi;

import com.fexco.postcoder.webrestcache.business.IrishAddressService;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * This is a Rest Front Controller implementation that receives external calls from clients of
 * this app and redirect them to the corresponding service.
 * This controller was created specifically for Irish Address calls.
 * @author Emival Junior
 * @version 1.0
 */
@RestController
public class IrishAddressController {

    @Autowired
    private IrishAddressService irishAddressService;

    /**
     * Receive calls for /address/ie/ path
     * @param address address of the search
     * @param allRequestParams optional request params of the search
     * @return result of the search
     */
    @RequestMapping(value = {UrlPrefixConstants.IRISH_ADDRESS_LOOKUP+"{address}",UrlPrefixConstants.IRISH_ADDRESS_LOOKUP})
    public @ResponseBody String lookupAddress(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return irishAddressService.lookupAddress(address.isPresent()?address.get():"",allRequestParams);
    }

    /**
     * Receive calls for /addressgeo/ie/ path
     * @param address address of the search
     * @param allRequestParams optional request params of the search
     * @return result of the search
     */
    @RequestMapping(value = {UrlPrefixConstants.IRISH_ADDRESS_GEO_LOOKUP+"{address}",UrlPrefixConstants.IRISH_ADDRESS_GEO_LOOKUP})
    public @ResponseBody String lookupAddressGeo(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return irishAddressService.lookupAddressGeo(address.isPresent()?address.get():"",allRequestParams);
    }

    /**
     * Receive calls for /position/ie/ path
     * @param address address of the search
     * @param allRequestParams optional request params of the search
     * @return result of the search
     */
    @RequestMapping(value = {UrlPrefixConstants.IRISH_ADDRESS_COORDINATE_LOOKUP+"{address}",UrlPrefixConstants.IRISH_ADDRESS_COORDINATE_LOOKUP})
    public @ResponseBody String lookupAddressCoordinate(@PathVariable Optional<String> address, @RequestParam Map<String,String> allRequestParams){
        return irishAddressService.lookupAddressCoordinate(address.isPresent()?address.get():"",allRequestParams);
    }

    /**
     * Receive calls for /rgeo/ie/ path
     * @param latitude Latitude of the search point
     * @param longitude Longitude of the search point
     * @param allRequestParams optional request params of the search
     * @return result of the search
     */
    @RequestMapping(value = {UrlPrefixConstants.IRISH_REVERSE_ADDRESS_GEO_LOOKUP+"{latitude}/{longitude:.+}",UrlPrefixConstants.IRISH_REVERSE_ADDRESS_GEO_LOOKUP})
    public @ResponseBody String lookupReverseAddressGeo(@PathVariable Optional<String> latitude,@PathVariable Optional<String> longitude, @RequestParam Map<String,String> allRequestParams){
        return irishAddressService.lookupReverseAddressGeo(
                latitude.isPresent()?latitude.get():"",
                longitude.isPresent()?longitude.get():"",
                allRequestParams);
    }
}
