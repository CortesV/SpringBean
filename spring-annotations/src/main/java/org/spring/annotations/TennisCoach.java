package org.spring.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("tennisCoach")
public class TennisCoach implements Coach{

	@Autowired
	@Qualifier("happyFortuneService")
	//@Qualifier("databaseFortuneService")
	//@Qualifier("restFortuneService")
	//@Qualifier("randomSevice")
	private FortuneService fortuneService;
	
	public TennisCoach() {
		System.out.println(">> TennisCoach: inside default constructor.");
	}
	
	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	public FortuneService getFortuneService() {
		return fortuneService;
	}

	public void setFortuneService(FortuneService theFortuneService) {
		System.out.println(">> TennisCoach: inside setFortuneService.");
		this.fortuneService = theFortuneService;
	}

	public void doSomeCrazyStuff(FortuneService theFortuneService) {
		System.out.println(">> TennisCoach: inside doSomeCrazyStuff.");
		this.fortuneService = theFortuneService;
	}
	
	@PostConstruct
	public void initMethod() {
		System.out.println(">> TennisCoach: inside of initMethod");
	}
	
	@PreDestroy
	public void destroyMethod() {
		System.out.println(">> TennisCoach: inside of destroyMethod");
	}
}
