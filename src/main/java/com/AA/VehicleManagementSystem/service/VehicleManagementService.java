package com.AA.VehicleManagementSystem.service;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import com.AA.VehicleManagementSystem.exception.InvalidVehicleException;
import com.AA.VehicleManagementSystem.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VehicleManagementService implements VehicleManagement {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        log.info("Vehicle added: " + vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(String vrn, Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vrn);
        if (optionalVehicle.isPresent()) {
            var updatedVehicle = vehicle.toBuilder().build();
            log.info("Updated vehicle: " + updatedVehicle);
            vehicleRepository.save(updatedVehicle);
            return updatedVehicle;
        }
        throw new InvalidVehicleException(String.format("Vehicle with VRN %s not found.", vrn));
    }

    @Override
    public List<Vehicle> getVehicles(List<String> vrnList) {
        var vehiclesByVrn = vehicleRepository.findAllById(vrnList);
        var existingVrnList = vehiclesByVrn.stream().map(Vehicle::getVrn).toList();
        vrnList.forEach(vrn -> {
                    if (!existingVrnList.contains(vrn)) {
                        log.info("Vehicle not found with VRN " + vrn);
                    }
                }
        );
        return vehiclesByVrn;
    }
}
