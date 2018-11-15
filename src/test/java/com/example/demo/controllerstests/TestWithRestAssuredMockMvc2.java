package com.example.demo.controllerstests;

import com.example.demo.Controller1;
import com.example.demo.TimeService;
import com.example.demo.model.TimeDto;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.ResponseOptions;
import org.junit.Before;
import org.junit.Test;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 - You can use RestAssured to test a controller without loading Spring
 */
public class TestWithRestAssuredMockMvc2 {

    private TimeService timeService = mock(TimeService.class);

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new Controller1(timeService));
    }

    @Test
    public void test_using_localhost() {
        // given
        when(timeService.getTimeAsString()).thenReturn(new TimeDto("11:22:33"));

        // when:
        ResponseOptions response = given().get("/gettime");

        // then:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThatJson(parsedJson).field("time").isEqualTo("11:22:33");

    }
}
