package com.fexco.postcoder.webrestcache.restapi;

import com.fexco.postcoder.webrestcache.AbstractTest;
import com.fexco.postcoder.webrestcache.business.UkAddressService;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;

import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.contains;

/**
 * Test class of UK Address Controller
 * @author Emival Junior
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "application.properties")
public class UkAddressControllerTest extends AbstractTest{

    @MockBean
    private UkAddressService ukAddressService;

    @Autowired
    private UkAddressController ukAddressController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLookupAddress() {
        given(this.ukAddressService
                .lookupAddress(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(UrlPrefixConstants.UK_ADDRESS_LOOKUP + "{address}",
                String.class, "D02X285",new HashMap<>());
        verify(ukAddressService, times(1)).lookupAddress("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",responseEntity.getBody());
    }

    @Test
    public void testLookupAddressThrowClientException() {
        given(this.ukAddressService
                .lookupAddress(contains("D02X285"), anyMap()))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(UrlPrefixConstants.UK_ADDRESS_LOOKUP + "{address}",
                String.class, "D02X285",new HashMap<>());
        verify(ukAddressService, times(1)).lookupAddress("D02X285", new HashMap<>());
        Assert.assertEquals(HttpStatus.FORBIDDEN,responseEntity.getStatusCode());
    }

    @Test
    public void testLookupAddressGeo() {
        given(this.ukAddressService
                .lookupAddressGeo(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(UrlPrefixConstants.UK_ADDRESS_GEO_LOOKUP + "{address}",
                String.class, "D02X285");
        verify(ukAddressService, times(1)).lookupAddressGeo("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",responseEntity.getBody());
    }
    @Test
    public void testLookupAddressGeoThrowClientException() {
        given(this.ukAddressService
                .lookupAddressGeo(contains("D02X285"), anyMap()))
                .willThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(UrlPrefixConstants.UK_ADDRESS_GEO_LOOKUP + "{address}",
                String.class, "D02X285",new HashMap<>());
        verify(ukAddressService, times(1)).lookupAddressGeo("D02X285", new HashMap<>());
        Assert.assertEquals(HttpStatus.FORBIDDEN,responseEntity.getStatusCode());
    }

}
