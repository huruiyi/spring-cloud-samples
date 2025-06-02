package vip.fairy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class FrontendApplication {

  public static void main(String[] args) {
    SpringApplication.run(FrontendApplication.class, args);
  }

  @Controller
  static class MyController {

    @Value("${order.url}")
    private String orderUrl;

    @Value("${product.url}")
    private String productUrl;

    @RequestMapping("/order")
    public @ResponseBody String green(RestTemplate restTemplate) {
      return getString(restTemplate, orderUrl);
    }

    @RequestMapping("/product")
    public @ResponseBody String blue(RestTemplate restTemplate) {
      return getString(restTemplate, productUrl);
    }

    private String getString(RestTemplate restTemplate, String url) {
      String result = restTemplate.getForObject(url, String.class);
      log.debug("result -> {}", result);
      return result;
    }

  }

}
