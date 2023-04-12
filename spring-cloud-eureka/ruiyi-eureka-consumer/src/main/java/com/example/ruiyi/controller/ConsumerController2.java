package com.example.ruiyi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author hurui
 */
@RestController
@RequestMapping("/restTemplate")
public class ConsumerController2 {

    private RestTemplate restTemplate;

    public ConsumerController2(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * http://localhost:8800/restTemplate/sayHello
     *
     * @return
     */
    @RequestMapping("/sayHello")
    public String sayHello() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://eureka-client-1/sayHello?userName={userName}")
                .build().expand("ddy")
                .encode();
        return restTemplate.getForObject(uriComponents.toUri().toString(), String.class);
    }

    /**
     * http://localhost:8800/restTemplate/serverInfo
     *
     * @return
     */
    @RequestMapping("/serverInfo")
    public String serverInfo() {
        return restTemplate.getForObject("http://eureka-client-1/" + "serverInfo", String.class);
    }

}