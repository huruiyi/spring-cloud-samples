package vip.fairy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Value("${server.port}")
  private long port;

  @GetMapping("/user")
  public String me() {
    return String.format("hello ,i am you,i from user-service:%d!", port);
  }

}
