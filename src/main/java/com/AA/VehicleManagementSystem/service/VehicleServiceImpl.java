package com.AA.VehicleManagementSystem.service;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import com.AA.VehicleManagementSystem.exception.InvalidVehicleException;
import com.AA.VehicleManagementSystem.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        if (vehicleRepository.findById(vehicle.getVrn()).isPresent()) {
            throw new InvalidVehicleException(String.format("Vehicle with VRN %s already exists.", vehicle.getVrn()));
        }
        log.info("New vehicle details added : " + vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(String vrn, Vehicle vehicle) {
        var currentVehicle = vehicleRepository.findById(vrn).orElseThrow(() -> new InvalidVehicleException(String.format("Vehicle with VRN %s not found.", vrn)));
        if (currentVehicle.equals(vehicle)) {
            log.info("Vehicle details are already updated. No changes required.");
            return currentVehicle;
        } else if (!currentVehicle.getVrn().equalsIgnoreCase(vrn)) {
            //vrn has changed. new record will be saved with new vrn provided. delete the record with old vrn
            vehicleRepository.deleteById(vrn);
        }
        //vehicle contains updated details for same vrn
        log.info("Vehicle details updated : " + vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getVehicles(List<String> vrnList) {
        var vehiclesByVrn = vehicleRepository.findAllById(vrnList);
        var existingVrnList = vehiclesByVrn.stream().map(Vehicle::getVrn).toList();
        var vrnNotFound = vrnList.stream().filter(vrn -> !existingVrnList.contains(vrn)).toList();
        if (!vrnNotFound.isEmpty()) {
            log.info("Vehicle not found with VRNs: " + vrnNotFound);
        }
        return vehiclesByVrn;
    }

}
