package com.example.demo.controllerstests;

//import com.jayway.restassured.http.ContentType;
//import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
//import static com.jayway.restassured.RestAssured.given;

import com.example.demo.TimeService;
import com.example.demo.model.TimeDto;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.ResponseOptions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
 - Load complete spring environment, including the Servlet container
 - You can mock any bean in the system, using @MockBean
 - With the Autowired @WebApplicationContext, you can configure RestAssuredMockMvc
 - In your tests you can use RestAssuredMockMvc if you like to use the RestAssured syntax
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWithRestAssuredMockMvc {

    @Autowired
    WebApplicationContext context;

    @MockBean
    private TimeService timeService;

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
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
