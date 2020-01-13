package com.lei.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
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
}
