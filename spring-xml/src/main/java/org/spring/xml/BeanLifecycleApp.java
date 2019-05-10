package org.spring.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-lifecycle-applicationContext.xml");
		Coach coach = context.getBean("myCoach", Coach.class);
		System.out.println(coach.getDailyWorkout());
		context.close();
	}
}
