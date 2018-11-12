package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@JsonTest
public class CustomerTest {

    @Autowired
    private JacksonTester<Customer> json;

    @Test
    public void testDeserialize() throws IOException {
        String jsonString = "{\"name\":\"Robbert\",\"age\":19}";
        Customer customer = json.parseObject(jsonString);
        assertThat(customer.getAge()).isEqualTo(19);
        assertThat(customer.getName()).isEqualTo("Robbert");
    }
    
    @Test
    public void testSerialize() throws IOException {
        Customer customer = new Customer("Robbert",19);
        // compare complete (exact) json
        assertThat(json.write(customer).getJson()).isEqualTo("{\"name\":\"Robbert\",\"age\":19}");

        // compare the content of the json, ignoring order of fields and whitespaces
        assertThat(json.write(customer)).isStrictlyEqualToJson("{\"age\" : 19, \"name\":\"Robbert\"}");

        // compare a string from the json
        assertThat(json.write(customer)).extractingJsonPathStringValue("name").isEqualTo("Robbert");

        // compare a number from the json
        assertThat(json.write(customer)).extractingJsonPathNumberValue("age").isEqualTo(19);
    }

}