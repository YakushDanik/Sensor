package com.testproject.sensor.controller;

import com.testproject.sensor.model.Measurement;
import com.testproject.sensor.service.MeasurementService;
import com.testproject.sensor.dto.MeasurementDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    MeasurementController(MeasurementService measurementService){
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMeasurement(@RequestBody MeasurementDTO measurementDTO) {
        measurementService.addMeasurement(measurementDTO);
        return ResponseEntity.ok("Measurement added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Measurement>> getAllMeasurements() {
        return ResponseEntity.ok(measurementService.getAllMeasurements());
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Long> getRainyDaysCount() {
        return ResponseEntity.ok(measurementService.getRainyDaysCount());
    }
}
