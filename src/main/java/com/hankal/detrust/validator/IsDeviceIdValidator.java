package com.hankal.detrust.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsDeviceIdValidator implements ConstraintValidator<IsDeviceId, String> {

    private boolean required = false;

    public void initialize(IsDeviceId constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return DeviceValidator.isDevice(value);
        }

        return true;
    }

}
