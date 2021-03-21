package com.devcortes.grpc.spring_server_second.server;

import com.devcortes.grpc.spring_server_second.HelloRequest;
import com.devcortes.grpc.spring_server_second.HelloResponse;
import com.devcortes.grpc.spring_server_second.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

	@Override
	public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
		LOGGER.info("----------------------- GET REQUEST -----------------------");
		LOGGER.info("Request received from client: " + request);
		LOGGER.info("Received first name: " + request.getFirstName());
		LOGGER.info("Received last name: " + request.getLastName());

		String greeting = new StringBuilder().append("Hello, ")
				.append(request.getFirstName())
				.append(" ")
				.append(request.getLastName())
				.toString();

		HelloResponse response = HelloResponse.newBuilder()
				.setGreeting(greeting)
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
		LOGGER.info("----------------------- SEND RESPONSE -----------------------");
	}
}
