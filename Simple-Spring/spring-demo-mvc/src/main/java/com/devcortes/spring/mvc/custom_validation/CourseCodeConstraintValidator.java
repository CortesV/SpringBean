package com.devcortes.spring.mvc.custom_validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

	private String[] coursePrefixes;

	@Override
	public void initialize(CourseCode constraintAnnotation) {
		coursePrefixes = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		boolean result = false;
		for (String tempPrefix : coursePrefixes) {
			result = value.startsWith(tempPrefix);

			// if we found a match then break out of the loop
			if (result) {
				break;
			}
		}
		return result;
	}

}
