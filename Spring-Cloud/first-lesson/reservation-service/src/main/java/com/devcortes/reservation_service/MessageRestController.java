package com.devcortes.reservation_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController("")
public class MessageRestController {

    @Value("${message}")
    private String message;

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
