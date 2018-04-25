package com.hankal.detrust.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsUserIdValidator implements ConstraintValidator<IsUserId, String> {
    private boolean required = false;

    public void initialize(IsUserId constraintAnnotation) {
        required =
                constraintAnnotation.required();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required)
            return UserIdValidator.isUserId(value);

        return true;
    }
}

