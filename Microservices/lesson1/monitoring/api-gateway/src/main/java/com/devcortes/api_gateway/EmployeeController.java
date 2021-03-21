package com.devcortes.api_gateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("")
public class EmployeeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/employeeDetails/{employeeid}", produces = "application/json")
    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
    public ResponseEntity<String> getStudents(@PathVariable int employeeid) {
        System.out.println("Getting Employee details for " + employeeid);

        String response = restTemplate.exchange("http://employee-service/find-employee-details/{employeeid}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, employeeid).getBody();

        System.out.println("Response Body " + response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> fallbackMethod(int employeeid) {

        return new ResponseEntity<>("Fallback response:: No employee details available temporarily", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
