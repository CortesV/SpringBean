package com.devcortes.zipkinclient;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("")
@SpringBootApplication
public class ZipkinClientApplication {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinClientApplication.class, args);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        LOG.info("Client service receive request on hello");
        LOG.info("new log in http://localhost:8080");
        LOG.info("another new log in http://localhost:8080");
        return this.restTemplate().getForEntity("http://localhost:8082/hello", String.class);
    }
}
