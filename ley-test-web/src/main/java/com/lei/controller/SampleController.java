package com.lei.controller;

import com.lei.client.LogerViewerClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleController {
    @Autowired private LogerViewerClient logerViewerClient;
    @Autowired private RestTemplate restTemplate;

    @GetMapping("/sample/1")
    public String sampleOne(@RequestParam("test") String test) {
        int i = 0;
        i++;
        return String.valueOf(i);
    }

    @GetMapping("/sample/2")
    @SneakyThrows
    public String sampleTwo() {
        throw new Exception();
    }

    @GetMapping("/sample/3")
    @SneakyThrows
    public String sampleThree() {
        String r = logerViewerClient.listFolder(" the world");
        return r;
    }

    @GetMapping("/sample/4")
    @SneakyThrows
    public String sampleFour() {
        ResponseEntity<String> r = restTemplate.getForEntity("http://localhost:8081/logviewer-api/rest-template", String.class);
        return "";
    }
}
