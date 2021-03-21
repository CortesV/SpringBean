package com.devcortes.grpc.spring_grpc_server.server;

import com.devcortes.grpc.spring_grpc_server.Greeting;
import com.devcortes.grpc.spring_grpc_server.client.HelloServiceClient;
import com.devcortes.grpc.spring_grpc_server.HelloWorldServiceGrpc;
import com.devcortes.grpc.spring_grpc_server.Person;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

	@Autowired
	private HelloServiceClient helloServiceClient;

	@Override
	public void sayHello(Person request, StreamObserver<Greeting> responseObserver) {
		LOGGER.info("server received {}", request);

		String message = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";
		Greeting greeting = Greeting.newBuilder().setMessage(message).build();
		LOGGER.info("server responded {}", greeting);

		String response = helloServiceClient.hello(request.getFirstName(), request.getLastName());

		greeting = greeting.toBuilder().setMessage(greeting.getMessage() + " " + response).build();

		responseObserver.onNext(greeting);
		responseObserver.onCompleted();
	}
}
