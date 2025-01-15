package com.testproject.sensor;

import com.testproject.sensor.client.SensorClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorApplication.class, args);
		SensorClient client = new SensorClient("http://localhost:8080");

		String sensorName = "TestSensor";

		client.registerSensor(sensorName);
		client.sendMeasurements(sensorName, 1000);
		client.getAllMeasurements();
	}

}
