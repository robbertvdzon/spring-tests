package com.example.demo.controllerstests;

import com.example.demo.TimeService;
import com.example.demo.model.TimeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/*
 - Load complete spring environment, but without the Servlet container
 - You can mock any bean in the system, using @MockBean
 - You cannot create real rest call's, but you can use MockMvc to make requests
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc  // this is needed to autowire the MockMvc
public class TestWithMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeService timeService;

    @Test
    public void test_get_time() throws Exception {
        given(timeService.getTimeAsString()).willReturn(new TimeDto("11:22:33"));

        mockMvc.perform(get("/gettime")).andExpect(content().json("{\"time\": \"11:22:33\"}"));

    }

}
