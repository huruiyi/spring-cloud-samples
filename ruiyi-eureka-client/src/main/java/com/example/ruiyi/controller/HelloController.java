package com.example.ruiyi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hurui
 */
@RestController
public class HelloController {

    @Value("${spring.application.name}")
    private String application;

    @Value("${server.port}")
    private String port;


    @RequestMapping("/hello")
    public String hello() {
        return "Hello World" + " ,from " + "application:" + application + " port:" + port;
    }

    @RequestMapping("/sayHello")
    public String sayHello(String userName) {
        String str = "你好:" + userName + " ,from " + "application:" + application + " port:" + port;
        return str;
    }

    @RequestMapping("/serverInfo")
    public String serverInfo() {
        String str = "application:" + application + " port:" + port;
        return str;
    }


}
