package com.devcortes;

public class TrackCoach implements Coach{

	private FortuneService fortuneService;
	
	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return "Just do it: " + fortuneService.getFortune();
	}

	public void initMethod() {
		System.out.println("Init method of trackCoach");
	}
	
	public void destroyMethod() {
		System.out.println("Destroy method of trackCoach");
	}
}
