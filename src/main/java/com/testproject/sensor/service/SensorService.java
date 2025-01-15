package com.testproject.sensor.service;

import com.testproject.sensor.model.Sensor;
import com.testproject.sensor.repository.SensorRepository;
import com.testproject.sensor.dto.SensorDTO;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    SensorService(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    public void register(SensorDTO sensorDTO){
        if (sensorRepository.existsByName(sensorDTO.getName())) {
            throw new RuntimeException("sensor by that name already exists!");
        }
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        sensorRepository.save(sensor);
    }
}
