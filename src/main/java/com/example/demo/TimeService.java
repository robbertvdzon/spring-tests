package com.example.demo;

import com.example.demo.model.TimeDto;
import com.example.demo.model.UtcDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.RequestEntity.get;

@Service
public class TimeService {

    private RestTemplate restTemplate;

    @Autowired
    public TimeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TimeDto getTimeAsString() {
        RequestEntity<Void> request = get(URI.create("http://worldclockapi.com/api/json/utc/now")).build();
        ResponseEntity<UtcDateTime> exchange = restTemplate.exchange(request, UtcDateTime.class);


        Matcher matcher = Pattern.compile("\\d\\d:\\d\\d:\\d\\d").matcher(exchange.getBody().currentDateTime);
        if (matcher.find()) {
            return new TimeDto(matcher.group());
        }
        return null;
    }
}


