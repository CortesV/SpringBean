package com.devcortes.spring.mvc.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping("")
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		return "index";
	}
	
	@RequestMapping("main-menu")
	public String showMyHomePage() {
		return "main-menu";
	}
	
	@RequestMapping("show-form")
	public String showForm() {
		return "show-form";
	}
	
	@RequestMapping("process-form")
	public String processForm() {
		return "process-form";
	}
	
	@RequestMapping("process-form-version-two")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		
		String theName = request.getParameter("studentName");
		theName = theName.toUpperCase();
		String result = "Yo! " + theName;
		model.addAttribute("message", result);
		
		return "process-form";
	}
	
	@RequestMapping("process-form-version-three")
	public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {
		
		theName = theName.toUpperCase();
		String result = "Hey My Friend from v3! " + theName;
		model.addAttribute("message", result);
		
		return "process-form";
	}
}
