package com.example.demo.controllerstests;

import com.example.demo.TimeService;
import com.example.demo.model.TimeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/*
 - Load only the web components of spring:
    - @Controller,
    - @ControllerAdvice,
    - @JsonComponent,
    - Converter/GenericConverter,
    - Filter,
    - WebMvcConfigurer
    - HandlerMethodArgumentResolver
 - It does NOT load:
    -  @Component,
     - @Service
     - @Repositor

 - The components that are not loaded needs to be mocked
 - You can use MockMvc to make requests using relative URL's

 If you are looking to load your full application configuration and use MockMVC,
 you should consider @SpringBootTest combined with @AutoConfigureMockMvc rather than this annotation.

 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class TestWithMockMvc3 {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TimeService timeService;

    @Test
    public void testTime() throws Exception {
        given(timeService.getTimeAsString()).willReturn(new TimeDto("19:03"));
        mockMvc.perform(get("/gettime")).andExpect(content().json("{\"time\": \"19:03\"}"));
    }
}

