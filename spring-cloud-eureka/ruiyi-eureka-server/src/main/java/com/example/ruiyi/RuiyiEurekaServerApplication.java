package com.example.ruiyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableEurekaServer
public class RuiyiEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuiyiEurekaServerApplication.class, args);
    }

}
