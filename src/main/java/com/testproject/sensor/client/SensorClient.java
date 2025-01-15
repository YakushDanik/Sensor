package com.testproject.sensor.client;

import com.testproject.sensor.dto.MeasurementDTO;
import com.testproject.sensor.dto.SensorDTO;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Random;
import java.util.stream.IntStream;

public class SensorClient {

    private final WebClient webClient;

    public SensorClient(String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public void registerSensor(String sensorName) {
        try {
            webClient.post()
                    .uri("/sensors/registration")
                    .bodyValue(new SensorDTO(sensorName))
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnSuccess(response -> System.out.println("Sensor registered: " + response))
                    .block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                System.out.println("Sensor already registered.");
            } else {
                throw e;
            }
        }
    }

    public void sendMeasurements(String sensorName, int count) {
        Random random = new Random();
        IntStream.range(0, count).forEach(i -> {
            MeasurementDTO measurement = new MeasurementDTO(
                    random.nextDouble() * 100, // Temperature between 0 and 100
                    random.nextBoolean(),      // Random boolean for raining
                    new SensorDTO(sensorName)
            );

            webClient.post()
                    .uri("/measurements/add")
                    .bodyValue(measurement)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnSuccess(response -> System.out.println("Measurement added: " + response))
                    .block();
        });
    }

    public void getAllMeasurements() {
        webClient.get()
                .uri("/measurements")
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> System.out.println("All measurements: " + response))
                .block();
    }
}

