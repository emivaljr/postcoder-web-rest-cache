package com.fexco.postcoder.webrestcache.business;

import com.fexco.postcoder.webrestcache.AbstractTest;
import com.fexco.postcoder.webrestcache.infra.constants.UrlPrefixConstants;
import com.fexco.postcoder.webrestcache.integration.IrishAddressConsumer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class of Irish Address Service
 * @author Emival Junior
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "application.properties")
public class IrishAddressServiceTest extends AbstractTest {

    @Autowired
    private IrishAddressService irishAddressService;

    @MockBean
    private IrishAddressConsumer irishAddressConsumer;


    @Test
    public void testLookupAddress() throws Exception {
        given(this.irishAddressConsumer
                .lookupAddress(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        String response = this.irishAddressService.lookupAddress("D02X285", new HashMap<>());
        verify(irishAddressConsumer, times(1)).lookupAddress("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",response);
    }

    @Test
    public void testLookupAddressGeo() throws Exception {
        given(this.irishAddressConsumer
                .lookupAddressGeo(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        String response = this.irishAddressService.lookupAddressGeo("D02X285", new HashMap<>());
        verify(irishAddressConsumer, times(1)).lookupAddressGeo("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",response);
    }

    @Test
    public void testLookupAddressCoordinate() throws Exception {
        given(this.irishAddressConsumer
                .lookupAddressCoordinate(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        String response = this.irishAddressService.lookupAddressCoordinate("D02X285", new HashMap<>());
        verify(irishAddressConsumer, times(1)).lookupAddressCoordinate("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",response);
    }

    @Test
    public void testLookupReverseAddressGeo() throws Exception {
        given(this.irishAddressConsumer
                .lookupReverseAddressGeo(contains("53.332067"),contains("53.332067"), anyMap()))
                .willReturn("testWithCache");
        String response  = this.irishAddressService.lookupReverseAddressGeo("53.332067","53.332067", new HashMap<>());
        verify(irishAddressConsumer, times(1)).lookupReverseAddressGeo("53.332067","53.332067", new HashMap<>());
        Assert.assertEquals("testWithCache",response);
    }

}