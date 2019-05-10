package com.devcortes;

import org.springframework.stereotype.Component;

@Component
public class RandomSevice implements FortuneService{

	@Override
	public String getFortune() {
		return "RandomSevice";
	}

}
