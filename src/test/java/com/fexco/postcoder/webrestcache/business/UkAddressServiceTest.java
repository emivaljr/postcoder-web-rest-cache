package com.fexco.postcoder.webrestcache.business;

import com.fexco.postcoder.webrestcache.AbstractTest;
import com.fexco.postcoder.webrestcache.integration.UkAddressConsumer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class of Uk Address Service
 * @author Emival Junior
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "application.properties")
public class UkAddressServiceTest extends AbstractTest {

    @MockBean
    private UkAddressConsumer ukAddressConsumer;

    @Autowired
    private UkAddressService ukAddressService;


    @Test
    public void testLookupAddress() throws Exception {
        given(this.ukAddressConsumer
                .lookupAddress(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        String response = this.ukAddressService.lookupAddress("D02X285", new HashMap<>());
        verify(ukAddressConsumer, times(1)).lookupAddress("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",response);

    }

    @Test
    public void testLookupAddressGeo() throws Exception {
        given(this.ukAddressConsumer
                .lookupAddressGeo(contains("D02X285"), anyMap()))
                .willReturn("testWithCache");
        String response = this.ukAddressService.lookupAddressGeo("D02X285", new HashMap<>());
        verify(ukAddressConsumer, times(1)).lookupAddressGeo("D02X285", new HashMap<>());
        Assert.assertEquals("testWithCache",response);
    }

}