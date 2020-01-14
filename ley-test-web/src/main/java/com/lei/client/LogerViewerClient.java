package com.lei.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="whatIsThis", url = "http://localhost:8081/logviewer-api")
public interface LogerViewerClient {
    @GetMapping("/file/list")
    String listFolder(@RequestParam(value="hello") String hello);
}
