package com.springboot.j.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

	private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).+$";
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value != null && value.matches(PASSWORD_PATTERN);
	}

}
