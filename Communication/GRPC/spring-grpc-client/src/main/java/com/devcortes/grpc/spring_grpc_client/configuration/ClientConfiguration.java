package com.devcortes.grpc.spring_grpc_client.configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

	@Bean
	public ManagedChannel helloWorldChannel() {
		return ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
	}
}
