package com.AA.VehicleManagementSystem.repository;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}
