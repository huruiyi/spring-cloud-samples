package com.example.ruiyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /*
     * http://localhost:20000/hello
     */
    @GetMapping("/hello")
    public String hello() {
        return restTemplate.getForObject("http://provider/" + "hello", String.class);
    }

    /**
     * <a href="http://localhost:20000/instance">instance</a>
     */
    @GetMapping("/instance")
    public String LoadInstance() {
        /* *
         * restTemplate.getForObject()与loadBalancerClient.choose不能放在一个方法中，因为restTemplate.getForObject()包含了choose方法
         */
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        return serviceInstance.toString();
    }

}
