package com.example.client.product.api;

import com.example.client.product.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    protected Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);


    private final DiscoveryClient client;

    public ProductEndpoint(DiscoveryClient client) {
        this.client = client;
    }

    @RequestMapping(value = "/")
    public List<Product> products() {
        List<ServiceInstance> eurekaClient1 = client.getInstances("EurekaClient1");
        for (ServiceInstance serviceInstance : eurekaClient1) {
            System.out.println(serviceInstance.getUri() + " " + serviceInstance.getPort());
        }
        List<Product> products = new ArrayList<>();

        Product product;
        for (int i = 0; i < 10; i++) {
            product = new Product();
            product.setId(i);
            product.setName("产品" + i);
            product.setPrice(10 + i);
            products.add(product);
        }
        return products;
    }
}
