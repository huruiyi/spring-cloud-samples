package vip.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(p -> p.path("/order").uri("lb://order-service"))
        .route(p -> p.path("/product").uri("lb://product-service"))
        .build();
  }

  /**
   * 错误处理:TODO
   */
  @Controller
  static class FailedController {
    @ResponseBody
    @RequestMapping("/failed")
    public String failed() {
      return "Service is not available. Please try again later.";
    }
  }
}
