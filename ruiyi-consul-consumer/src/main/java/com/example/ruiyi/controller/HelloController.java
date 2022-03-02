package com.example.ruiyi.controller;

import com.example.models.Article;
import com.example.models.Res;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;

/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: 调用服务接口
 */
@RestController
public class HelloController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;


    private String serviceId = "service-provider";

    /**
     * 获取所有服务提供者
     * http://localhost:8506/instances-lists
     */
    @GetMapping("/instances-lists")
    public Object instancesLists() {
        return discoveryClient.getInstances(serviceId);
    }


    /**
     * 获取所有注册服务名称
     * http://localhost:8506/services-lists
     */
    @GetMapping("/services-lists")
    public Object servicesLists() {
        return discoveryClient.getServices();
    }


    /**
     * 从所有服务中选择一个服务（轮询）
     * http://localhost:8506/poll-service
     */
    @GetMapping("/poll-service")
    public Object pollService() {
        return loadBalancer.choose(serviceId).getUri().toString();
    }


    /**
     * 调用服务提供者接口
     * http://localhost:8506/hello
     */
    @GetMapping("/hello")
    public String hello() {
        ServiceInstance serviceInstance = loadBalancer.choose(serviceId);
        URI uri = serviceInstance.getUri();
        String callService = new RestTemplate().getForObject(uri + "/hello", String.class);
        System.out.println(callService);
        return callService;
    }

    /**
     * 调用服务提供者接口
     * http://localhost:8506/sayHello?userName=ake
     */
    @GetMapping("/sayHello")
    public String sayHello(String userName) {
        ServiceInstance serviceInstance = loadBalancer.choose(serviceId);
        URI uri = serviceInstance.getUri();
        String callService = new RestTemplate().getForObject(uri + "/sayHello?userName=" + userName, String.class);
        System.out.println(callService);
        return callService;
    }


    @GetMapping("/addArticle")
    public Res addArticle(String userName) throws JsonProcessingException {
        ServiceInstance serviceInstance = loadBalancer.choose(serviceId);
        URI uri = serviceInstance.getUri();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        Article article = new Article();
        article.setId(1l);
        article.setName("百年孤独");
        article.setContent("《百年孤独》，是哥伦比亚作家加西亚·马尔克斯创作的长篇小说，是其代表作，也是拉丁美洲魔幻现实主义文学的代表作，被誉为“再现拉丁美洲历史社会图景的鸿篇巨著”。");

        ObjectMapper mapper = new ObjectMapper();
        String requestParams = mapper.writeValueAsString(article);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestParams, requestHeaders);
        Res res = new RestTemplate().postForObject(uri + "/addArticle", requestEntity, Res.class);

        System.out.println(res.toString());
        return res;
    }


}