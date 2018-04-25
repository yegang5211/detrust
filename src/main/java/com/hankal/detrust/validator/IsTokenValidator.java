package com.hankal.detrust.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsTokenValidator implements ConstraintValidator<IsToken, String> {
    private boolean required = false;

    public void initialize(IsToken constraintAnnotation) {
        required =
                constraintAnnotation.required();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required)
            return TokenValidator.isToken(value);

        return true;
    }
}

