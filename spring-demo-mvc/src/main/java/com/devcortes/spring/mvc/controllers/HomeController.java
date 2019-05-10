package com.devcortes.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping("main-menu")
	public String showMyHomePage() {
		return "main-menu";
	}
}
