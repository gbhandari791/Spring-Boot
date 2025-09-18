package com.blog.validation.validator;

import com.blog.validation.annotation.StrongPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

	private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$";
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return value != null && value.matches(PASSWORD_PATTERN);
	}

}
