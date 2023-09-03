package com.mp.restapiclient.services;

import com.mp.restapiclient.models.Sensor;
import com.mp.restapiclient.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findSensorByName(String name) {
        return sensorRepository.findSensorByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensor.setCreatedAt(LocalDateTime.now());
        sensorRepository.save(sensor);
    }
}
