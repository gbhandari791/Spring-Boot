package com.blog.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.blog.validation.validator.StrongPasswordValidator;


@Constraint(validatedBy = StrongPasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {

	String message() default "Password must be 8-15 characters long and include at least one uppercase, one lowercase, one number, and one special character.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
