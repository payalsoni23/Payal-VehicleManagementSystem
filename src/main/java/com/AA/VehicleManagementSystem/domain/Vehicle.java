package com.AA.VehicleManagementSystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @Column(name = "VRN")
    private String vrn;

    @Column(name = "Make")
    private String make;

    @Column(name = "Model")
    private String model;

    @Column(name = "Vehicle Year")
    private Integer vehicleYear;

    @Column(name = "fuelType")
    private String fuelType;
}
