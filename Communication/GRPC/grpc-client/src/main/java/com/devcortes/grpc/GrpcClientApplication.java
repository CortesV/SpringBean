package com.devcortes.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GrpcClientApplication {

	public static void main(String[] args) throws IOException {
		while (true) {
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
					.usePlaintext()
					.build();
			com.devcortes.grpc.HelloServiceGrpc.HelloServiceBlockingStub stub
					= com.devcortes.grpc.HelloServiceGrpc.newBlockingStub(channel);

			BufferedReader reader =
					new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please type in your name: ");
			String firstName = reader.readLine();
			System.out.println();
			System.out.print("Please type in your last name: ");
			String lastName = reader.readLine();


			com.devcortes.grpc.HelloResponse helloResponse = stub.hello(com.devcortes.grpc.HelloRequest.newBuilder()
					.setFirstName(firstName)
					.setLastName(lastName)
					.build());

			System.out.println("Response received from server:\n" + helloResponse);
			channel.shutdown();
		}
	}
}
