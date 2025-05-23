package com.example.ruiyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class RuiyiConfigServerApplication {

  /**
   * <a href="http://localhost:8001/config/pro">...</a>
   * <a href="http://localhost:8001/config/dev">...</a>
   * <p>
   * <a href="http://localhost:8001/config-pro.properties">...</a>
   * <a href="http://localhost:8001/config-dev.properties">...</a>
   */
  public static void main(String[] args) {
    SpringApplication.run(RuiyiConfigServerApplication.class, args);
  }

}
