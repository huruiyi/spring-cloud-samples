package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class YiConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(YiConfigServer.class, args);
    }

}
