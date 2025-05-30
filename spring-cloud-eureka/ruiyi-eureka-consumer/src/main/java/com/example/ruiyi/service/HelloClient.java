package com.example.ruiyi.service;

import com.example.ruiyi.config.FeignConfiguration;
import feign.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: 服务接口
 * name:远程服务名，即服务提供者spring.application.name中配置的名称
 * 此类中的方法和远程服务中Contoller中的方法名和参数需保持一致。
 */
@FeignClient(contextId = "eureka-client", name = "eureka-client")
public interface HelloClient {

    @RequestMapping("/hello")
    public String hello();

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam("userName") String name);

    @RequestMapping(value = "/serverInfo")
    public String serverInfo();
}
