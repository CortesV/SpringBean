package com.devcortes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Coach theCoach = applicationContext.getBean("tennisCoach", Coach.class);
		Coach theCoach2 = applicationContext.getBean("tennisCoach", Coach.class);
		
		System.out.println(theCoach == theCoach2);
		System.out.println(theCoach);
		System.out.println(theCoach2);
		
		applicationContext.close();
	}
}
