package com.AA.VehicleManagementSystem.controller;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import com.AA.VehicleManagementSystem.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicleManagementSystem")
@Tag(name = "Vehicles")
public class VehicleController {


    @Autowired
    VehicleService vehicleManagementService;

    @Operation(description = "REST endpoint to add new vehicle data", responses = {@ApiResponse(description = "Success", responseCode = "201"), @ApiResponse(description = "Bad Request", responseCode = "400")})
    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        var newVehicle = vehicleManagementService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
    }

    @Operation(description = "REST endpoint to update vehicle data", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Bad Request", responseCode = "400")})
    @PutMapping("/updateVehicle")
    public ResponseEntity<Vehicle> updateVehicle(@RequestParam String vrn, @RequestBody Vehicle vehicle) {
        var updatedVehicle = vehicleManagementService.updateVehicle(vrn, vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
    }

    @Operation(description = "REST endpoint to list vehicles data for given VRNs", responses = {@ApiResponse(description = "Success", responseCode = "200")})
    @GetMapping("/find")
    public ResponseEntity<List<Vehicle>> getVehicles(@RequestParam List<String> vrnList) {
        var vehicles = vehicleManagementService.getVehicles(vrnList);
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }
}