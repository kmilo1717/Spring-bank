package com.practice.cardmanagement.card_service.validations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Constraint(validatedBy = ExpiryDateValidation.ExpiryDateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpiryDateValidation {

    String message() default "invalid expiry date";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class ExpiryDateValidator implements ConstraintValidator<ExpiryDateValidation, String> {
        private int yearRange;
        private boolean onlyFutureDates;

        @Override
        public void initialize(ExpiryDateValidation constraintAnnotation) {
            yearRange = constraintAnnotation.yearRange();
            onlyFutureDates = constraintAnnotation.onlyFutureDates();
        }
        @Override
        public boolean isValid(String expiryDate, ConstraintValidatorContext constraintValidatorContext) {
            if (expiryDate == null || expiryDate.isEmpty()) {
                setDefaultMessage(constraintValidatorContext, "cannot be empty");
                return false;
            }

            try {
                YearMonth expiryDateYearMonth = YearMonth.parse(expiryDate, DateTimeFormatter.ofPattern("MM/yyyy"));
                YearMonth currentYearMonth = YearMonth.now();

                if (onlyFutureDates && expiryDateYearMonth.isBefore(currentYearMonth)) {
                    setDefaultMessage(constraintValidatorContext, "cannot be before current date");
                    return false;
                }

                if (expiryDateYearMonth.isAfter(currentYearMonth.plusYears(yearRange))) {
                    setDefaultMessage(constraintValidatorContext, "cannot be more than " + yearRange + " years");
                    return false;
                }
                return true;
            } catch (DateTimeParseException e) {
                setDefaultMessage(constraintValidatorContext, "must be MM/yyyy format");
                return false;
            }
        }

        private void setDefaultMessage(ConstraintValidatorContext context, String message) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }
    }

    boolean onlyFutureDates() default false;

    int yearRange() default 0;
}
