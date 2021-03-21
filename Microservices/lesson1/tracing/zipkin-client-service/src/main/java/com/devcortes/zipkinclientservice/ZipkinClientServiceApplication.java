package com.devcortes.zipkinclientservice;

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

@RestController
@RequestMapping("")
@SpringBootApplication
public class ZipkinClientServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(ZipkinClientServiceApplication.class);

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinClientServiceApplication.class, args);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("return hello to endpoint http://localhost:8080 ");
        log.info("new log http://localhost:8082 ");
        log.info("another new log http://localhost:8082 ");
        return new ResponseEntity<>("Hello!!!!!", HttpStatus.OK);
    }

}
