package com.devcortes.grpc.spring_grpc_client;

import com.devcortes.grpc.spring_grpc_client.client.HelloWorldClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringGrcpClientApplicationTests {

	@Autowired
	private HelloWorldClient helloWorldClient;

	@Test
	public void testSayHello() {
		assertThat(helloWorldClient.sayHello("John", "Doe")).isEqualTo("Hello John Doe! Hello, John Doe");
	}

	@Test
	public void contextLoads() {
	}

}
