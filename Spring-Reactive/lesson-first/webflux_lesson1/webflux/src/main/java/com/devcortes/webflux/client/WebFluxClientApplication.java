package com.devcortes.webflux.client;

import com.devcortes.webflux.server.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootApplication
public class WebFluxClientApplication {

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    public CommandLineRunner commandLineRunner(WebClient webClient) {
        return args -> {
            webClient.get().uri("/events")
                    .accept(MediaType.TEXT_EVENT_STREAM)
                    .retrieve()
                    .bodyToFlux(Event.class)
                    .subscribe(System.out::println);
        };
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebFluxClientApplication.class)
                .properties(Collections.singletonMap("server.port", "8082"))
                .run(args);
    }
}
