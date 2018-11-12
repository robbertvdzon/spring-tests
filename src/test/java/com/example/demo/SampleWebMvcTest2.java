package com.example.demo;

import com.example.demo.model.TimeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
/*
@SpringBootTest and @AutoConfigureMockMvc loads a complete Application context
 */
public class SampleWebMvcTest2 {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TimeService timeService;

    @Test
    public void test1() throws Exception {
        given(timeService.getTimeAsString()).willReturn(new TimeDto("19:03"));

        mockMvc.perform(get("/gettime")).andExpect(content().json("{\"time\": \"19:03\"}"));
    }

    @Test
    public void test2() throws Exception {
        mockMvc.perform(get("/getSampleCustomer")).andExpect(content().json("{\"name\": \"Robbert\"}"));
    }


}

