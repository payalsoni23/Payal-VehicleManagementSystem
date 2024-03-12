package com.AA.VehicleManagementSystem.service;

import com.AA.VehicleManagementSystem.domain.Vehicle;

import java.util.List;

public interface VehicleManagement {
    Vehicle addVehicle(Vehicle vehicle);

    Vehicle updateVehicle(String vrn, Vehicle vehicle);

    List<Vehicle> getVehicles(List<String> vrnList);

}
