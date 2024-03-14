package com.AA.VehicleManagementSystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Vehicle Management System", version = "1.0", description = "REST API for managing vehicles data"))
public class VehicleManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleManagementSystemApplication.class, args);
    }

}
