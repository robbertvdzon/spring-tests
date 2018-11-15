package com.example.demo.controllerstests;

import com.example.demo.TimeService;
import com.example.demo.model.TimeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/*
 - Load complete spring environment, including the Servlet container
 - You can mock any bean in the system, using @MockBean
 - With the Autowired @TestRestTemplate, you can make rest call's to the system with relative URL's

 - The TestRestTemplate is a wrapper around the RestTemplate
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWithTestRestTemplate {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TimeService timeService;

    @Test
    public void test_get_time() {
        // given
        given(timeService.getTimeAsString()).willReturn(new TimeDto("11:22:33"));

        // when
        ResponseEntity<TimeDto> exchange = restTemplate.exchange("/gettime", HttpMethod.GET, null, TimeDto.class);

        // then
        assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
        assertThat(exchange.getBody().time).isEqualTo("11:22:33");
    }
}
