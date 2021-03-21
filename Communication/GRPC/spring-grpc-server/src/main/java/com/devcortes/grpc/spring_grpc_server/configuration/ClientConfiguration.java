package com.devcortes.grpc.spring_grpc_server.configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

	@Bean
	public ManagedChannel helloServiceChannel() {
		return ManagedChannelBuilder.forAddress("localhost", 7575).usePlaintext().build();
	}
}
