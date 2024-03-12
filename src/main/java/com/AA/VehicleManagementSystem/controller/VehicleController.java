package com.AA.VehicleManagementSystem.controller;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import com.AA.VehicleManagementSystem.service.VehicleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    VehicleManagementService vehicleManagementService;

    //   @Operation(summary = "Add new vehicle details.")
    @PostMapping("/add-vehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        var newVehicle = vehicleManagementService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
    }

    //   @Operation(summary = "Update vehicle with given VRN.")
    @PutMapping("/update-vehicle")
    public ResponseEntity<Vehicle> updateVehicle(@RequestParam String vrn, @RequestBody Vehicle vehicle) {
        var updatedVehicle = vehicleManagementService.updateVehicle(vrn, vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
    }

    //    @Operation(summary = "Find all the vehicles with given VRN.")
    @GetMapping("/find-all")
    public ResponseEntity<List<Vehicle>> getVehicles(@RequestParam List<String> vrnList) {
        var vehicles = vehicleManagementService.getVehicles(vrnList);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(vehicles);
        }
    }

}
