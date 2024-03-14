package com.AA.VehicleManagementSystem.controller;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import com.AA.VehicleManagementSystem.exception.InvalidVehicleException;
import com.AA.VehicleManagementSystem.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @MockBean
    VehicleService vehicleServiceImpl;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void addVehiclewithNewVrn() throws Exception {
        var vehicle = getSampleVehicle();
        when(vehicleServiceImpl.addVehicle(vehicle)).thenReturn(vehicle);
        mockMvc.perform(post("/vehicleManagementSystem/addVehicle").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().isCreated());
    }

    @Test
    public void addVehicleWithExistingVrn() throws Exception {
        var vehicle = getSampleVehicle();
        when(vehicleServiceImpl.addVehicle(vehicle)).thenThrow(InvalidVehicleException.class);
        mockMvc.perform(post("/vehicleManagementSystem/addVehicle").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().is4xxClientError());
    }

    @Test
    public void addVehicleWithInvalidVrn() throws Exception {
        var vehicle = getSampleVehicle();
        vehicle.setVrn("_&*");
        mockMvc.perform(post("/vehicleManagementSystem/addVehicle").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().is4xxClientError());
    }

    @Test
    public void addVehicleWithInvalidMake() throws Exception {
        var vehicle = getSampleVehicle();
        vehicle.setMake("abc-123.6");
        mockMvc.perform(post("/vehicleManagementSystem/addVehicle").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().is4xxClientError());
    }

    @Test
    public void addVehicleWithInvalidModel() throws Exception {
        var vehicle = getSampleVehicle();
        vehicle.setModel("abc-123.6");
        mockMvc.perform(post("/vehicleManagementSystem/addVehicle").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().is4xxClientError());
    }

    @Test
    public void addVehicleWithInvalidYear() throws Exception {
        var vehicle = getSampleVehicle();
        vehicle.setVehicleYear(20);
        mockMvc.perform(post("/vehicleManagementSystem/addVehicle").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().is4xxClientError());
    }

    @Test
    public void addVehicleWithInvalidFuelType() throws Exception {
        var vehicle = getSampleVehicle();
        vehicle.setFuelType("abc");
        mockMvc.perform(post("/vehicleManagementSystem/addVehicle").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().is4xxClientError());
    }

    @Test
    public void updateVehicleWithExistingVRN() throws Exception {
        var vehicle = getSampleVehicle();
        when(vehicleServiceImpl.updateVehicle("TEST1", vehicle)).thenReturn(vehicle);
        mockMvc.perform(put("/vehicleManagementSystem/updateVehicle").param("vrn", "TEST1").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.vrn").value("TEST1")).andExpect(jsonPath("$.model").value("testModel")).andExpect(jsonPath("$.make").value("testMake")).andExpect(jsonPath("$.vehicleYear").value(2024)).andExpect(jsonPath("$.fuelType").value("petrol"));
    }

    @Test
    public void updateVehicleWithNonExistingVRN() throws Exception {
        var vehicle = getSampleVehicle();
        when(vehicleServiceImpl.updateVehicle("INVALID", vehicle)).thenThrow(InvalidVehicleException.class);
        mockMvc.perform(put("/vehicleManagementSystem/updateVehicle").param("vrn", "INVALID").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicle))).andExpect(status().is4xxClientError());
    }

    @Test
    public void getVehiclesbyVRNWithValidVRNs() throws Exception {
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

        when(vehicleServiceImpl.getVehicles(List.of("VAND2019", "NOLO2022", "HOSR2016"))).thenReturn(vehicles);
        mockMvc.perform(get("/vehicleManagementSystem/find").param("vrnList", "VAND2019", "NOLO2022", "HOSR2016").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicles))).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].vrn").value("VAND2019")).andExpect(jsonPath("$[1].vrn").value("NOLO2022")).andExpect(jsonPath("$[2].vrn").value("HOSR2016"));

    }

    @Test
    public void getVehiclesbyVRNWithInValidVRNs() throws Exception {
        var vehicle1 = getSampleVehicle();
        vehicle1.setVrn("VAND2019");

        var vehicle3 = getSampleVehicle();
        vehicle3.setVrn("HOSR2016");

        var vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle3);

        when(vehicleServiceImpl.getVehicles(List.of("VAND2019", "INVALIDVRN", "HOSR2016"))).thenReturn(vehicles);
        mockMvc.perform(get("/vehicleManagementSystem/find").param("vrnList", "VAND2019", "INVALIDVRN", "HOSR2016").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(vehicles))).andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].vrn").value("VAND2019")).andExpect(jsonPath("$[1].vrn").value("HOSR2016"));

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