package com.example.demo;

import com.example.demo.model.TimeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller1 {

    TimeService timeService;

    @Autowired
    public Controller1(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/gettime")
    public TimeDto getTime() {
        return timeService.getTimeAsString();
    }


}
