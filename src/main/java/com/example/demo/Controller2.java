package com.example.demo;

import com.example.demo.model.TimeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller2 {

    @GetMapping("/getSampleCustomer")
    public Customer getSampleCustomer() {
        return new Customer("Robbert", 19);
    }

}
