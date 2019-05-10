package com.devcortes;

public class CricketCoach implements Coach{

	private FortuneService foruneService;
	private String emailAddress;
	private String team;
	
	public CricketCoach() {
		System.out.println("CricketCoach: inside no-arg constructor");
	}
	
	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes";
	}

	@Override
	public String getDailyFortune() {
		return foruneService.getFortune();
	}

	public FortuneService getForuneService() {
		return foruneService;
	}

	public void setForuneService(FortuneService foruneService) {
		System.out.println("CricketCoach: inside setter method - setForuneService");
		this.foruneService = foruneService;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		System.out.println("CricketCoach: inside setter method - setEmailAddress");
		this.emailAddress = emailAddress;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		System.out.println("CricketCoach: inside setter method - setTeam");
		this.team = team;
	}
}
