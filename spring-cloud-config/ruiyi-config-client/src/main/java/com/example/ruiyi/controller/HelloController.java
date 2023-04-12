package com.example.ruiyi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @Value("${app.version}")
    private String version;

    @Value("${server.port}")
    private String port;

    @Value("${message}")
    private String message;

    @RequestMapping("/hello")
    public String from() {
        return "version：" + this.version + " port：" + this.port + "<br/>" + message;
    }
}