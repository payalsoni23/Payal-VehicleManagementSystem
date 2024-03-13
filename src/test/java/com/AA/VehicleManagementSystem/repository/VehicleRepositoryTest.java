package com.AA.VehicleManagementSystem.repository;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void saveVehicle() {
        var vehicle = new Vehicle();
        vehicle.setVrn("TEST1");
        vehicle.setModel("test model");
        vehicle.setMake("test make");
        vehicle.setVehicleYear(2024);
        vehicle.setFuelType("petrol");
        vehicleRepository.save(vehicle);
        var fetchVehicle = vehicleRepository.findById("TEST1").get();
        assertEquals(vehicle.getVrn(), fetchVehicle.getVrn());
        assertEquals(vehicle.getMake(), fetchVehicle.getMake());
        assertEquals(vehicle.getModel(), fetchVehicle.getModel());
        assertEquals(vehicle.getVehicleYear(), fetchVehicle.getVehicleYear());
        assertEquals(vehicle.getFuelType(), fetchVehicle.getFuelType());
    }

    @Test
    void updateVehicle() {
        var currentVehicle = vehicleRepository.findById("BMOL2022").get();
        currentVehicle.setMake("test make");
        vehicleRepository.save(currentVehicle);
        var updatedVehicle = vehicleRepository.findById("BMOL2022").get();
        assertEquals(currentVehicle.getVrn(), updatedVehicle.getVrn());
        assertEquals(currentVehicle.getMake(), updatedVehicle.getMake());
    }

    @Test
    void fetchMultipleVehicles() {
        var vehiclesByVrn = vehicleRepository.findAllById(List.of("SUFT2521", "AUES5024", "NOLO2026"));
        assertEquals(3, vehiclesByVrn.size());
    }

    @Test
    void fetchNoVehicles() {
        var vehiclesByVrn = vehicleRepository.findAllById(List.of(" "));
        assertTrue(vehiclesByVrn.isEmpty());
    }
}