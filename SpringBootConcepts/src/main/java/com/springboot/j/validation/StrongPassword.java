package com.springboot.j.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {

	String message() default "Password must contain at least 1 uppercase, 1 digit, and 1 special char";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
