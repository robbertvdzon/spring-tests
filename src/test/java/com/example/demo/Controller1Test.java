package com.example.demo;

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

@RunWith(SpringRunner.class)
@WebMvcTest(Controller1.class)
/*
  WebMvcTest spins up all the spring MVC machinery, EXCEPT the actual Servlet container. The requests to the
  controller that is testes goes through the MockMvc object.
  All injected beans must be mocked with @MockBean
 */
public class Controller1Test {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TimeService timeService;

    @Test
    public void test1() throws Exception {
        given(timeService.getTimeAsString()).willReturn(new TimeDto("19:03"));

        mockMvc.perform(get("/gettime")).andExpect(content().json("{\"time\": \"19:03\"}"));
    }

}

