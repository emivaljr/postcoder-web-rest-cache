package com.fexco.postcoder.webrestcache.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

/**
 * Created by emival on 17/09/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IrishAdressRepositoryTest {

    @MockBean
    private RestTemplate irishAddressRestTemplate;

    @Autowired
    private IrishAdressRepository irishAdressRepository;

    @Test
    public void testLookupIrishAddress(){
        given(this.irishAddressRestTemplate.getForEntity(anyString(),eq(String.class),anyMap())).willReturn(new ResponseEntity<String>("teste",HttpStatus.OK));
        Map<String, String> params = new HashMap<String, String>();
        params.put("lines","3");
        params.put("format","json");
        String response = irishAdressRepository.lookupIrishAddress("D02X285",params);
        assertThat(response == "teste");
    }

}
