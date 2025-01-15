package com.testproject.sensor.controller;

import org.junit.jupiter.api.Test;

import com.testproject.sensor.service.SensorService;
import com.testproject.sensor.dto.SensorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

class SensorControllerTest {

    private SensorService sensorService;
    private SensorController sensorController;

    @BeforeEach
    void setUp() {
        sensorService = mock(SensorService.class);
        sensorController = new SensorController(sensorService);
    }

    @Test
    void registerSensor_shouldReturnSuccessMessage() {
        SensorDTO sensorDTO = new SensorDTO();
        doNothing().when(sensorService).register(sensorDTO);

        ResponseEntity<String> response = sensorController.registerSensor(sensorDTO);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Sensor registered successfully", response.getBody());
        verify(sensorService, times(1)).register(sensorDTO);
    }
}