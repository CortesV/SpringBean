package com.devcortes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println("First coach: ");
		Coach theCoach = applicationContext.getBean("myCoach", Coach.class);
		System.out.println("\t" + theCoach.getDailyWorkout());
		System.out.println("\t" + theCoach.getDailyFortune());
		
		System.out.println("Second coach:");
		Coach theCoach2 = applicationContext.getBean("myCoach2", Coach.class);
		System.out.println("\t" + theCoach2.getDailyWorkout());
		System.out.println("\t" + theCoach2.getDailyFortune());
		
		System.out.println("Third coach:");
		CricketCoach theCoach3 = applicationContext.getBean("myCoach3", CricketCoach.class);
		System.out.println("\t" + theCoach3.getEmailAddress());
		System.out.println("\t" + theCoach3.getTeam());
		System.out.println("\t" + theCoach3.getDailyWorkout());
		System.out.println("\t" + theCoach3.getDailyFortune());
		
		applicationContext.close();
	}
}
