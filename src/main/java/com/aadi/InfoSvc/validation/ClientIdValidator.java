package com.aadi.InfoSvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Validator which will be used by ClientIdChecker constraint to validate the
 *
 * @author Aditya Kumar
 */
public class ClientIdValidator implements ConstraintValidator<ClientIdChecker, String> {

    /**
     * This method will determine if the value for the client id is valid or invalid.
     * It will determine that the value should not be null, should not be blank and
     * should have a length between four and sixteen characters.
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return - true if the value hold the constraint, false otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final Predicate<String> checker = id -> !id.isBlank() && (id.length() > 4 && id.length() <= 16);
        return Optional.ofNullable(value)
                .filter(checker::test)
                .isPresent();
    }
}
