package com.example.ruiyi.controller;

import com.example.ruiyi.service.HelloClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hurui
 */
@RestController
@RequestMapping("feign")
public class ConsumerController1 {

  private final HelloClient helloClient;

  public ConsumerController1(HelloClient helloClient) {
    this.helloClient = helloClient;
  }

  /**
   * http://localhost:8800/feign/hello
   */
  @RequestMapping("/hello")
  public String hello() {
    return helloClient.hello();
  }

  /**
   * http://localhost:8800/feign/serverInfo
   */
  @RequestMapping("/serverInfo")
  public String index() {
    return helloClient.serverInfo();
  }

  /**
   * http://localhost:8800/feign/sayHello?userName=ndy
   */
  @RequestMapping("/sayHello")
  public String sayHello(String userName) {
    return helloClient.sayHello(userName);
  }

}
