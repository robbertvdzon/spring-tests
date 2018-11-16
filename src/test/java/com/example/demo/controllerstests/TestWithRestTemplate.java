package com.example.demo.controllerstests;

import com.example.demo.TimeService;
import com.example.demo.model.TimeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/*
 - Load complete spring environment, including the Servlet container
 - You can mock any bean in the system, using @MockBean
 - With the Autowired @TestRestTemplate, you can make rest call's to the system with relative URL's

 - The TestRestTemplate is a wrapper around the RestTemplate
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TestWithRestTemplate {

    @LocalServerPort
    int randomServerPort;

    private RestTemplate restTemplate = new RestTemplate();

    @MockBean
    private TimeService timeService;

    @Test
    public void test_get_time() {
        // given
        given(timeService.getTimeAsString()).willReturn(new TimeDto("11:22:33"));

        // when
        String url = String.format("http://localhost:%d/gettime", randomServerPort);
        ResponseEntity<TimeDto> exchange = restTemplate.exchange(url, HttpMethod.GET, null, TimeDto.class);

        // then
        assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
        assertThat(exchange.getBody().time).isEqualTo("11:22:33");
    }
}
