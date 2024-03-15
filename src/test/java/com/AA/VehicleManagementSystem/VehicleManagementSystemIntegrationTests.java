package com.AA.VehicleManagementSystem;

import com.AA.VehicleManagementSystem.domain.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleManagementSystemIntegrationTests {

    @LocalServerPort
    private int port;
    private StringBuilder baseUrl = new StringBuilder("http://localhost");

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl.append(":").append(port).append("/vehicleManagementSystem");
    }

    @Test
    public void testAddValidVehicle() {
        baseUrl.append("/addVehicle");
        var vehicle = getSampleVehicle();
        var response = restTemplate.postForObject(baseUrl.toString(), vehicle, Vehicle.class);
        assertAll(() -> assertNotNull(response), () -> assertEquals(response.getVrn(), (vehicle.getVrn())), () -> assertEquals(response.getMake(), (vehicle.getMake())), () -> assertEquals(response.getModel(), (vehicle.getModel())), () -> assertEquals(response.getFuelType(), (vehicle.getFuelType())), () -> assertEquals(response.getVehicleYear(), (vehicle.getVehicleYear())));
    }

    @Test
    public void testUpdateValidVehicle() {
        baseUrl.append("/updateVehicle");
        var vehicle = getSampleVehicle();
        vehicle.setVrn("VAND2012");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestEntity = new HttpEntity<>(vehicle, headers);
        UriComponentsBuilder url = UriComponentsBuilder.fromUriString(baseUrl.toString()).queryParam("vrn", "VAND2012");
        var response = restTemplate.exchange(url.toUriString(), HttpMethod.PUT, requestEntity, Vehicle.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testFindVehicles() {
        baseUrl.append("/find");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestEntity = new HttpEntity<>(headers);
        UriComponentsBuilder url = UriComponentsBuilder.fromUriString(baseUrl.toString()).queryParam("vrnList", "VAND2012");
        var responseEntity = restTemplate.exchange(url.toUriString(), HttpMethod.GET, requestEntity, List.class);
        assertNotNull(responseEntity.getBody());
        assertFalse(responseEntity.getBody().isEmpty());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testFindVehiclesWithInvalidVRN() {
        baseUrl.append("/find");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestEntity = new HttpEntity<>(headers);
        UriComponentsBuilder url = UriComponentsBuilder.fromUriString(baseUrl.toString()).queryParam("vrnList", "");
        var vehicle = getSampleVehicle();
        var responseEntity = restTemplate.exchange(url.toUriString(), HttpMethod.GET, requestEntity, List.class);
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().isEmpty());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
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
