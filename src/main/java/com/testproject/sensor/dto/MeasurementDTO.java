package com.testproject.sensor.dto;

import com.testproject.sensor.model.Sensor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {
    private SensorDTO sensor;
    private double value;
    private boolean raining;
    private LocalDateTime timestamp;

    public MeasurementDTO(double value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        raining = raining;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
