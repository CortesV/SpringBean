package com.devcortes.grpc;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends com.devcortes.grpc.HelloServiceGrpc.HelloServiceImplBase {

	@Override
	public void hello(com.devcortes.grpc.HelloRequest request, StreamObserver<com.devcortes.grpc.HelloResponse> responseObserver) {
		System.out.println("Request received from client:\n" + request);

		String greeting = new StringBuilder().append("Hello, ")
				.append(request.getFirstName())
				.append(" ")
				.append(request.getLastName())
				.toString();

		com.devcortes.grpc.HelloResponse response = com.devcortes.grpc.HelloResponse.newBuilder()
				.setGreeting(greeting)
				.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
