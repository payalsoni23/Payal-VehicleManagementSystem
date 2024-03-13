package com.AA.VehicleManagementSystem.controller;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import com.AA.VehicleManagementSystem.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicleManagementSystem")
public class VehicleController {

    @Autowired
    VehicleService vehicleManagementService;

    //   @Operation(summary = "Add new vehicle details.")
    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        var newVehicle = vehicleManagementService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
    }

    //   @Operation(summary = "Update vehicle with given VRN.")
    @PutMapping("/updateVehicle")
    public ResponseEntity<Vehicle> updateVehicle(@RequestParam String vrn, @RequestBody Vehicle vehicle) {
        var updatedVehicle = vehicleManagementService.updateVehicle(vrn, vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
    }

    //    @Operation(summary = "Find all the vehicles with given VRN.")
    @GetMapping("/find")
    public ResponseEntity<List<Vehicle>> getVehicles(@RequestParam List<String> vrnList) {
        var vehicles = vehicleManagementService.getVehicles(vrnList);
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }
}