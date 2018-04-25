package com.hankal.detrust.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsUserIdValidator.class})
public @interface IsUserId {
    boolean required() default true;

    String message() default "用户标识（UserId）不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
