package com.devcortes.consumer;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.time.Instant;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public RSocketRequester requester(RSocketStrategies rSocketStrategies) {
        return RSocketRequester.builder()
                .rsocketFactory(clientRSocketFactory -> clientRSocketFactory
                        .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .frameDecoder(PayloadDecoder.ZERO_COPY))
                .rsocketStrategies(rSocketStrategies)
                .connect(TcpClientTransport.create((new InetSocketAddress("127.0.0.1", 7000))))
                .retry()
                .block();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingsRequest {
    private String name;
}

@Data
class GreetingsResponse {

    private String greeting;

    public GreetingsResponse() {

    }

    GreetingsResponse(String name) {
        this.withGreeting("Hello " + name + " @ " + Instant.now());
    }

    public GreetingsResponse withGreeting (String message) {
        this.greeting = message;
        return this;
    }
}

@RestController
class ConsumerRestController {

    private final RSocketRequester requester;

    public ConsumerRestController(RSocketRequester requester) {
        this.requester = requester;
    }

    @GetMapping("/greeting/{name}")
    public Publisher<GreetingsResponse> greeting(@PathVariable("name") String name) {
        return this.requester
                .route("greeting")
                .data(new GreetingsRequest(name))
                .retrieveMono(GreetingsResponse.class);
    }

    @GetMapping(value = "greeting/sse/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<GreetingsResponse> greetingSSE(@PathVariable("name") String name) {
        return this.requester
                .route("greeting-stream")
                .data(new GreetingsRequest(name))
                .retrieveFlux(GreetingsResponse.class);
    }

    @GetMapping("/error")
    public Publisher<GreetingsResponse> error() {
        return this.requester
                .route("error")
                .data(Mono.empty())
                .retrieveFlux(GreetingsResponse.class);
    }
}