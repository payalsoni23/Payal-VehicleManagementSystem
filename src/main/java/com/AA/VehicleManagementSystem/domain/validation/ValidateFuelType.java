package com.AA.VehicleManagementSystem.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FuelTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull(message = "Fuel type can not be null")
@ReportAsSingleViolation
public @interface ValidateFuelType {
    String[] fuelTypes();

    String message() default "Invalid fuel type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
