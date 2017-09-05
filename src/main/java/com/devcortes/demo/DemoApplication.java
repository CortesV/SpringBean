package com.devcortes.demo;

import com.devcortes.demo.quoter.IQuoter;
import com.devcortes.demo.quoter.TerminatorQuoter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan("com.devcortes.demo")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(DemoApplication.class, args);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		context.getBean(IQuoter.class).sayQuote();
	}
}
