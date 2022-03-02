package com.example.ruiyi.controller;

import com.example.ruiyi.service.HelloClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hurui
 */
@RestController
public class ConsumerController {

    private HelloClient helloClient;

    public ConsumerController(HelloClient helloClient) {
        this.helloClient = helloClient;
    }

    @RequestMapping("/serverInfo")
    public String index() {
        return helloClient.serverInfo();
    }

    @RequestMapping("/hello")
    public String hello() {
        return helloClient.hello();
    }

    @RequestMapping("/sayHello")
    public String sayHello(String userName) {
        return helloClient.sayHello(userName);
    }
}