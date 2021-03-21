package com.devcortes.springreactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
public class WebSocketConfiguration {

	@Bean
	SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler) {
		return new SimpleUrlHandlerMapping(){
			{
				setUrlMap(Map.of("ws/greetings", webSocketHandler));
				setOrder(10);
			}
		};
	}

	@Bean
	WebSocketHandlerAdapter webSocketHandlerAdapter() {
		return new WebSocketHandlerAdapter();
	}

	@Bean
	WebSocketHandler webSocketHandler(GreetingProducer greetingProducer) {
		return webSocketSession -> {
			Flux<WebSocketMessage> response = webSocketSession
					.receive()
					.map(WebSocketMessage::getPayloadAsText)
					.map(GreetingRequest::new)
					.flatMap(greetingProducer::greet)
					.map(GreetingResponse::getResponse)
					.map(webSocketSession::textMessage);
			return webSocketSession
					.send(response);
		};
	}
}
