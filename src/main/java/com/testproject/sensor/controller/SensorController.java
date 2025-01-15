package com.testproject.sensor.controller;

import com.testproject.sensor.service.SensorService;
import com.testproject.sensor.dto.SensorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerSensor(@RequestBody SensorDTO sensorDTO) {
        sensorService.register(sensorDTO);
        return ResponseEntity.ok("Sensor registered successfully");
    }
}