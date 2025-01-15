package com.testproject.sensor.service;

import com.testproject.sensor.model.Measurement;
import com.testproject.sensor.model.Sensor;
import com.testproject.sensor.repository.MeasurementRepository;
import com.testproject.sensor.repository.SensorRepository;
import com.testproject.sensor.dto.MeasurementDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public void addMeasurement(MeasurementDTO measurementDTO) {
        Sensor sensor = sensorRepository.findByName(measurementDTO.getSensor().getName())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        Measurement measurement = new Measurement();
        measurement.setValue(measurementDTO.getValue());
        measurement.setRaining(measurementDTO.isRaining());
        measurement.setSensor(sensor);
        measurement.setTimestamp(LocalDateTime.now());

        measurementRepository.save(measurement);
    }

    public long getRainyDaysCount() {
        return measurementRepository.countByRainingTrue();
    }

}
