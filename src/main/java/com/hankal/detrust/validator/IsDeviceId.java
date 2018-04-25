package com.hankal.detrust.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yegang5211 on 2018/2/11.
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsDeviceIdValidator.class})
public @interface IsDeviceId {
    boolean required() default true;

    String message() default "手机设备标识格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
