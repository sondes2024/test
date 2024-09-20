package com.pmu.coursesmanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MinSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinSizeConstraint {
    String message() default "The input list cannot contain less than 3 horses.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}