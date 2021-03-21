package com.devcortes.grpc.spring_grpc_client.client;

import com.devcortes.grpc.spring_grpc_server.Greeting;
import com.devcortes.grpc.spring_grpc_server.HelloWorldServiceGrpc;
import com.devcortes.grpc.spring_grpc_server.Person;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldClient.class);

	@Autowired
	private ManagedChannel helloWorldChannel;

	public String sayHello(String firstName, String lastName) {
		Person person = Person.newBuilder().setFirstName(firstName).setLastName(lastName).build();
		LOGGER.info("client sending {}", person);

		HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub = HelloWorldServiceGrpc.newBlockingStub(helloWorldChannel);
		Greeting greeting = helloWorldServiceBlockingStub.sayHello(person);
		LOGGER.info("client received {}", greeting);

		return greeting.getMessage();
	}
}
