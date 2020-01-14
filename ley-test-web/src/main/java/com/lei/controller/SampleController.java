package com.lei.controller;

import com.lei.client.LogerViewerClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @Autowired private LogerViewerClient logerViewerClient;

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
}
