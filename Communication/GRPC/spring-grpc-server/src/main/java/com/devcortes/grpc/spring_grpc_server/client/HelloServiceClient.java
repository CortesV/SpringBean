package com.devcortes.grpc.spring_grpc_server.client;

import com.devcortes.grpc.spring_server_second.HelloRequest;
import com.devcortes.grpc.spring_server_second.HelloResponse;
import com.devcortes.grpc.spring_server_second.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceClient.class);

	@Autowired
	private ManagedChannel helloServiceChannel;

	public String hello(String firstName, String lastName) {
		LOGGER.info("Get name - " +  firstName + ", last name - " + lastName);
		LOGGER.info("Send information to server");
		HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(helloServiceChannel);
		HelloResponse hello = helloServiceBlockingStub.hello(HelloRequest.newBuilder().setFirstName(firstName).setLastName(lastName).build());

		return hello.getGreeting();
	}
}
