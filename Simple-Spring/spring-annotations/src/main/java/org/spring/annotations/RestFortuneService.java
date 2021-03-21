package org.spring.annotations;

import org.springframework.stereotype.Component;

@Component
public class RestFortuneService implements FortuneService{

	@Override
	public String getFortune() {
		return "RestFortuneService";
	}

}
