package com.practice.cardmanagement.card_service.validations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidation.NameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValidation {

    String message() default "must contain a name and a surname";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class NameValidator implements ConstraintValidator<NameValidation, String> {
        @Override
        public boolean isValid(String name, ConstraintValidatorContext context) {
            if (name == null || name.isEmpty()) {
                return false;
            }

            String[] nameParts = name.split(" ");
            if (nameParts.length != 2) {
                return false;
            }
            return true;
        }

    }
}
