package com.example.demo.controllerstests;

import com.example.demo.Controller1;
import com.example.demo.TimeService;
import com.example.demo.model.TimeDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/*
 Test a controller without loading the spring framework
 You can still use the mockMvc client for these tests
 */
public class TestWithMockMvc4 {

    private MockMvc mockMvc;

    private TimeService timeService = mock(TimeService.class);

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Controller1(timeService)).build();
    }

    @Test
    public void test1() throws Exception {
        given(timeService.getTimeAsString()).willReturn(new TimeDto("19:03"));

        mockMvc.perform(get("/gettime")).andExpect(content().json("{\"time\": \"19:03\"}"));
    }
}
