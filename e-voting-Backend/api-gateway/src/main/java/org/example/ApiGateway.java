package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;


@EnableDiscoveryClient
@SpringBootApplication
public class ApiGateway {
    public static void main(String[] args) {
    SpringApplication.run(ApiGateway.class, args);
    }
}