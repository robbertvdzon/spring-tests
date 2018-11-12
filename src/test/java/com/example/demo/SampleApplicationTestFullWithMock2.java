package com.example.demo;

import com.example.demo.model.TimeDto;
import com.example.demo.model.UtcDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleApplicationTestFullWithMock2 {

    @MockBean
    private RestTemplate clientRestTemplate;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void test_using_localhost() {
        ResponseEntity<Customer> exchange = restTemplate.exchange("/getSampleCustomer", HttpMethod.GET, null, Customer.class);
        assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void test_using_localhost2() {
        given(clientRestTemplate.exchange(any(), any(Class.class))).willReturn(new ResponseEntity(new UtcDateTime("11:22:11"), HttpStatus.OK));

        ResponseEntity<TimeDto> exchange = restTemplate.exchange("/gettime", HttpMethod.GET, null, TimeDto.class);
        assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
        assertThat(exchange.getBody().time).isEqualTo("11:22:11");
    }


}
