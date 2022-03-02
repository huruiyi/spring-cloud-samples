package com.example.ruiyi.controller;

import com.example.models.Article;
import com.example.models.Res;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    public String sayHello(@RequestParam(value = "userName") String userName) {
        String str = "你好:" + userName + " ,from " + "application:" + application + " port:" + port;
        return str;
    }

    @RequestMapping("/serverInfo")
    public String serverInfo() {
        String str = "application:" + application + " port:" + port;
        return str;
    }

    @PostMapping("/addArticle")
    public Res xx(@RequestBody Article article) throws JsonProcessingException {
        System.out.println(article.toString());
        Res res = new Res();
        res.setCode("200");
        res.setMessage("添加成功");

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(article);

        res.setData("added data:" + result);
        return res;
    }
}
