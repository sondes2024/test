package com.pmu.coursesmanager.validation;

import com.pmu.coursesmanager.dto.PartantDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class MinSizeConstraintValidator implements ConstraintValidator<MinSizeConstraint, List<PartantDto>> {
    @Override
    public boolean isValid(List<PartantDto> values, ConstraintValidatorContext context) {
        return values.size() >= 3;
    }
}