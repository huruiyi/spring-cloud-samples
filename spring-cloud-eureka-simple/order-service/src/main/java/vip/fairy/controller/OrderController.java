package vip.fairy.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class OrderController {

  private final DiscoveryClient discoveryClient;
  private final RestClient restClient;

  public OrderController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
    this.discoveryClient = discoveryClient;
    restClient = restClientBuilder.build();
  }

  @GetMapping("hello")
  public String helloWorld() {
    ServiceInstance serviceInstance = discoveryClient.getInstances("user-service").get(0);
    URI uri = serviceInstance.getUri();
    return restClient.get().uri(uri + "/user")
        .retrieve()
        .body(String.class);
  }
}
