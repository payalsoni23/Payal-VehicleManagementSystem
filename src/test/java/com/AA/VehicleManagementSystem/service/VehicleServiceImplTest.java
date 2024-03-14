package com.AA.VehicleManagementSystem.service;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import com.AA.VehicleManagementSystem.exception.InvalidVehicleException;
import com.AA.VehicleManagementSystem.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VehicleServiceImplTest {
    @MockBean
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleService vehicleService;

    @Test
    public void addVehicleWithNewVRN() {
        var vehicle = getSampleVehicle();
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        assertEquals(vehicle, vehicleService.addVehicle(vehicle));
    }

    @Test
    public void addVehicleWithExistingVRN() {
        var vehicle = getSampleVehicle();
        when(vehicleRepository.save(vehicle)).thenThrow(InvalidVehicleException.class);
        assertThrows(InvalidVehicleException.class, () -> vehicleService.addVehicle(vehicle), "Vehicle with VRN TEST1 already exists.");
    }

    @Test
    public void updateVehicleWithExistingVRN() {
        var vehicle = getSampleVehicle();
        when(vehicleRepository.findById(vehicle.getVrn())).thenReturn(Optional.of(vehicle));
        assertEquals(vehicle, vehicleService.updateVehicle("TEST1", vehicle));
    }

    @Test
    public void updateVehicleWithNewVRN() {
        var vehicle = getSampleVehicle();
        when(vehicleRepository.findById(vehicle.getVrn())).thenReturn(Optional.empty());
        assertThrows(InvalidVehicleException.class, () -> vehicleService.updateVehicle("TEST2", vehicle), "Vehicle with VRN TEST2 not found.");
    }

    @Test
    public void getVehiclesWithValidVRN() {
        var vehicle1 = getSampleVehicle();
        vehicle1.setVrn("VAND2019");

        var vehicle2 = getSampleVehicle();
        vehicle2.setVrn("NOLO2022");

        var vehicle3 = getSampleVehicle();
        vehicle3.setVrn("HOSR2016");

        var vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);

        when(vehicleRepository.findAllById(List.of("VAND2019", "NOLO2022", "HOSR2016"))).thenReturn(vehicles);
        var vehicleList = vehicleService.getVehicles(List.of("VAND2019", "NOLO2022", "HOSR2016"));
        assertEquals(vehicles, vehicleList);
        assertEquals(3, vehicleList.size());
    }

    @Test
    public void getVehiclesWithInValidVRN() {
        var vehicle1 = getSampleVehicle();
        vehicle1.setVrn("VAND2019");

        var vehicle2 = getSampleVehicle();
        vehicle2.setVrn("NOLO2022");

        var vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        when(vehicleRepository.findAllById(List.of("VAND2019", "NOLO2022", "INVALID"))).thenReturn(vehicles);
        var vehicleList = vehicleService.getVehicles(List.of("VAND2019", "NOLO2022", "INVALID"));
        assertEquals(2, vehicleList.size());
    }

    @Test
    public void getVehiclesWithNoVRN() {
        when(vehicleRepository.findAllById(List.of(" ", "INVALID"))).thenReturn(new ArrayList<>());
        var vehicleList = vehicleService.getVehicles(List.of(" ", "INVALID"));
        assertEquals(0, vehicleList.size());
    }

    private Vehicle getSampleVehicle() {
        var vehicle = new Vehicle();
        vehicle.setVrn("TEST1");
        vehicle.setModel("testModel");
        vehicle.setMake("testMake");
        vehicle.setVehicleYear(2024);
        vehicle.setFuelType("petrol");
        return vehicle;
    }
}