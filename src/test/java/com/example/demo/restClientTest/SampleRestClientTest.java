package com.example.demo.restClientTest;

import com.example.demo.TimeService;
import com.example.demo.model.TimeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/*
 Test a web client service that has the RestClient injected.
 The MockRestServiceServer will mock the responses that are expected from the RestClient
 */
@RunWith(SpringRunner.class)
@RestClientTest({TimeService.class})
@AutoConfigureWebClient(registerRestTemplate = true)
public class SampleRestClientTest {

    @Autowired
    TimeService timeService;

    @Autowired
    MockRestServiceServer server;

    @Test
    public void test1() {
        // given
        server
                .expect(requestTo("http://worldclockapi.com/api/json/utc/now"))
                .andRespond(
                        MockRestResponseCreators.withSuccess("{\"currentDateTime\":\"myDate\"}", MediaType.APPLICATION_JSON)
                );

        // when
        TimeDto timeAsString = timeService.getTimeAsString();

        // then
        assertThat(timeAsString).isEqualTo("myDate");
    }

}