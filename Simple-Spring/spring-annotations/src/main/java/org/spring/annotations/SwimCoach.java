package org.spring.annotations;

import org.springframework.beans.factory.annotation.Value;

public class SwimCoach implements Coach{

	private FortuneService fortuneService;
	
	@Value("${email.address}")
	private String email;
	
	@Value("${team}")
	private String team;
	
	public SwimCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return ">> SwimCoach getDailyWorkout()";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
}
