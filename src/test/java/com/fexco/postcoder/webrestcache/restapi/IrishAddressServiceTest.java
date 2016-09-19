package com.fexco.postcoder.webrestcache.restapi;

import com.fexco.postcoder.webrestcache.AbstractTest;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import com.fexco.postcoder.webrestcache.integration.IrishAddressConsumer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.contains;

import static org.mockito.BDDMockito.*;

/**
 * Created by emival on 19/09/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "application.properties")
public class IrishAddressServiceTest  extends AbstractTest{

    @MockBean
    private IrishAddressConsumer irishAddressConsumer;

    @Autowired
    private IrishAddressService irishAddressService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLookupAddress() {
        given(this.irishAddressConsumer
                .lookupAddress(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(UrlPrefixConstants.IRISH_ADDRESS_LOOKUP + "{address}",
                String.class, "D02X285");
        verify(irishAddressConsumer, times(1)).lookupAddress("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",responseEntity.getBody());
    }

    @Test
    public void testLookupAddressGeo() {
        given(this.irishAddressConsumer
                .lookupAddressGeo(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(UrlPrefixConstants.IRISH_ADDRESS_GEO_LOOKUP + "{address}",
                String.class, "D02X285");
        verify(irishAddressConsumer, times(1)).lookupAddressGeo("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",responseEntity.getBody());
    }

    @Test
    public void testLookupReverseAddressGeo() {
        given(this.irishAddressConsumer
                .lookupReverseAddressGeo(contains("53.332067"),contains("53.332067"), anyMap()))
                .willReturn("testWithCache");
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(UrlPrefixConstants.IRISH_REVERSE_ADDRESS_GEO_LOOKUP + "{latitude}/{longitude}",
                String.class, "53.332067","53.332067");
        verify(irishAddressConsumer, times(1)).lookupReverseAddressGeo("53.332067","53.332067", new HashMap<>());
        Assert.assertEquals("testWithCache",responseEntity.getBody());
    }


}
