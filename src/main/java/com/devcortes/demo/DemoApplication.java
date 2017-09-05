package com.devcortes.demo;

import com.devcortes.demo.beans.ConfigAnnotation;
import com.devcortes.demo.beans.Greeting;
import com.devcortes.demo.beans.LifecycleProcessor;
import com.devcortes.demo.quoter.IQuoter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
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
