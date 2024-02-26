package com.aadi.InfoSvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Constraint which validates the client id.
 *
 * @author Aditya Kumar
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClientIdValidator.class)
public @interface ClientIdChecker {
    String message() default "Client ID error";
    Class<?>[] groups() default{ };
    Class<? extends Payload>[] payload() default { };
}
