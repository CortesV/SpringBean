package com.devcortes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan("com.devcortes")
@PropertySource("classpath:application.properties")
public class SportConfig {
	
	@Value("${email.address}")
	private String email;
	
	@Value("${team}")
	private String team;
	
	@Bean("sadFortuneService")
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	
	@Bean("swimCoach")
	public Coach swimCoach(FortuneService fortuneService) {
		return new SwimCoach(sadFortuneService());
	}
}
