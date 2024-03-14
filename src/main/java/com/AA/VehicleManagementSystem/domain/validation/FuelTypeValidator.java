package com.AA.VehicleManagementSystem.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class FuelTypeValidator implements ConstraintValidator<ValidateFuelType, String> {

    private List<String> valueList;

    @Override
    public void initialize(ValidateFuelType constraintAnnotation) {
        valueList = new ArrayList<>();
        for (String value : constraintAnnotation.fuelTypes()) {
            valueList.add(value.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(s.toUpperCase());
    }
}
