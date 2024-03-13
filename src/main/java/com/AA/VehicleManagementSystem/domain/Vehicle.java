package com.AA.VehicleManagementSystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "MAKE")
    private String make;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "VEHICLE_YEAR")
    private Integer vehicleYear;

    @Column(name = "FUEL_TYPE")
    private String fuelType;
}
