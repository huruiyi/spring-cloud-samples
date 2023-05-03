package com.example.ruiyi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String hello() {
        return port + ":Hello World";
    }

    @RequestMapping("/sayHello")
    public String sayHello(String userName) {
        String str = "你好:" + userName;
        return str;
    }
}
