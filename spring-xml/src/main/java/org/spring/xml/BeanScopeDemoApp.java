package org.spring.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-scope-applicationContext.xml");
		Coach coach1 = context.getBean("myCoach", Coach.class);
		
		Coach coach2 = context.getBean("myCoach", Coach.class);
		
		boolean result = coach1 == coach2;
		
		System.out.println(result);
		System.out.println(coach1);
		System.out.println(coach2);
		
		context.close();
	}
}
