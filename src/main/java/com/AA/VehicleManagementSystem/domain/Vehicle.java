package com.AA.VehicleManagementSystem.domain;

import com.AA.VehicleManagementSystem.domain.validation.ValidateFuelType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "VRN can be alphanumeric only. Invalid VRN entered.")
    @Column(name = "VRN")
    private String vrn;

    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Make can be alphanumeric only. Invalid Make entered.")
    @Column(name = "MAKE")
    private String make;

    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Model can be alphanumeric only. Invalid Model entered.")
    @Column(name = "MODEL")
    private String model;

    @Min(value = 1000, message = "Invalid vehicle year")
    @Max(value = 9999, message = "Invalid vehicle year")
    @Column(name = "VEHICLE_YEAR")
    private Integer vehicleYear;

    @ValidateFuelType(fuelTypes = {"Petrol", "Diesel", "Hybrid", "Electric"}, message = "Invalid fuel type entered.")
    @Column(name = "FUEL_TYPE")
    private String fuelType;
}
