package com.testproject.sensor.controller;

import org.junit.jupiter.api.Test;

import com.testproject.sensor.model.Measurement;
import com.testproject.sensor.service.MeasurementService;
import com.testproject.sensor.dto.MeasurementDTO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

class MeasurementControllerTest {

    private MeasurementService measurementService;
    private MeasurementController measurementController;

    @BeforeEach
    void setUp() {
        measurementService = mock(MeasurementService.class);
        measurementController = new MeasurementController(measurementService);
    }

    @Test
    void addMeasurement_shouldReturnSuccessMessage() {
        MeasurementDTO measurementDTO = new MeasurementDTO();
        doNothing().when(measurementService).addMeasurement(measurementDTO);

        ResponseEntity<String> response = measurementController.addMeasurement(measurementDTO);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Measurement added successfully", response.getBody());
        verify(measurementService, times(1)).addMeasurement(measurementDTO);
    }

    @Test
    void getAllMeasurements_shouldReturnListOfMeasurements() {
        List<Measurement> measurements = new ArrayList<>();
        when(measurementService.getAllMeasurements()).thenReturn(measurements);

        ResponseEntity<List<Measurement>> response = measurementController.getAllMeasurements();

        assertEquals(OK, response.getStatusCode());
        assertEquals(measurements, response.getBody());
        verify(measurementService, times(1)).getAllMeasurements();
    }

    @Test
    void getRainyDaysCount_shouldReturnRainyDaysCount() {
        long rainyDaysCount = 5;
        when(measurementService.getRainyDaysCount()).thenReturn(rainyDaysCount);

        ResponseEntity<Long> response = measurementController.getRainyDaysCount();

        assertEquals(OK, response.getStatusCode());
        assertEquals(rainyDaysCount, response.getBody());
        verify(measurementService, times(1)).getRainyDaysCount();
    }
}