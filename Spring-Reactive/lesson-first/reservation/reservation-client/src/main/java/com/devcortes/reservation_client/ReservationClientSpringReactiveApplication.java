package com.devcortes.reservation_client;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan("com.devcortes.reservation_client")
public class ReservationClientSpringReactiveApplication {

	@Bean
	WebClient webClient(WebClient.Builder builder) {
		return  builder.build();
	}

	@Bean
	RSocketRequester requester(RSocketRequester.Builder builder) {
		return builder.connect(TcpClientTransport.create(7070)).block();
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationClientSpringReactiveApplication.class, args);
	}

}
