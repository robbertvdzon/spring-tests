package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)  // port is defined in application.properties
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


// @Import(MyTestsConfiguration.class)  // use specific configuration

public class SampleApplicationTestMock {

    @Test
    public void contextLoads() {
    }

}
