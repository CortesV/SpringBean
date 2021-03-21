package com.devcortes.demo.quoter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		context.getBean(IQuoter.class).sayQuote();
	}
}
