package com.AA.VehicleManagementSystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @NotNull
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
