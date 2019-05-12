package com.devcortes.spring.mvc.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devcortes.spring.mvc.entity.Student;

@Controller
@RequestMapping("/student/")
public class StudentController {
	
	@Value("#{countryOptions}")
	private Map<String, String> countryOptions;
	
	@RequestMapping("show-form")
	public String showForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		model.addAttribute("countryOptions", countryOptions);
		return "student-form";
	}
	
	@RequestMapping("process-form")
	public ModelAndView processForm(@ModelAttribute("student") Student student) {
		System.out.println(student);
		ModelAndView view = new ModelAndView();
		view.setViewName("student-confirmation");
		view.addObject("student", student);
		return view;
	}
}
