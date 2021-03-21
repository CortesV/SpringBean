package com.devcortes.second_client_layer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class SecondClientLayerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondClientLayerServiceApplication.class, args);
    }

}
