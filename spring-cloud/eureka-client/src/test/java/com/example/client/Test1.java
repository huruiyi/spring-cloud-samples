package com.example.client;

import com.example.client.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = ClientApplication.class)
public class Test1 {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void demo1() {
        Product[] products = restTemplate.getForObject("http://EurekaClient1/products/", Product[].class);
        for (Product product : products) {
            System.out.println(product);
        }
    }




}
