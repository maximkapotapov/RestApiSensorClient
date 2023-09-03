package com.mp.sensorclient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mp.restapiclient.dto.MeasurementDTODate;
import com.mp.restapiclient.models.Measurement;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class SensorClient {
    private final static String newSensorURL = "http://localhost:8080/sensors/registration";
    private final static String addMeasurements = "http://localhost:8080/measurements/add";

    private final static String getMeasurements = "http://localhost:8080/measurements";

    public static void main(String[] args) {
//        postNewSensor();
//        post1000Measurements();
        getMeasurements();
    }

    static void postNewSensor() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> sensor = new HashMap<>();
        sensor.put("name", "Final Sensor");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(sensor);
        restTemplate.postForObject(newSensorURL, request, String.class);
    }

    static void post1000Measurements() {
        RestTemplate restTemplate =  new RestTemplate();
        Map<String, Object> measurement = new HashMap<>();
        Map<String, String> sensor = new HashMap<>();
        sensor.put("name", "Rest Sensor");
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(measurement);

        for(int i = 0; i <=1000; i++) {
            Random random = new Random();
            int maxValue = 100;
            int value = random.nextInt(maxValue) * (random.nextBoolean() ? -1 : 1);
            boolean raining = random.nextBoolean();
            measurement.put("value", value);
            measurement.put("raining", raining);
            measurement.put("sensor", sensor);
            restTemplate.postForObject(addMeasurements, request, String.class);
        }
    }

    static void getMeasurements() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<MeasurementDTODate>> responseList =
                restTemplate.exchange(getMeasurements, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<MeasurementDTODate>>() {
                        });
//                        MeasurementDTODate[] measurements = restTemplate.getForObject(getMeasurements, MeasurementDTODate[].class);
        List<MeasurementDTODate> measurements = responseList.getBody();
            for (MeasurementDTODate measurement : measurements) {
                if (measurement.getSensor().getName().equals("Rest Sensor")) {
                    System.out.println(measurement);
                };
            }
    }
}







