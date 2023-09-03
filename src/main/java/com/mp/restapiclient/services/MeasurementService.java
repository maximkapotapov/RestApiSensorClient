package com.mp.restapiclient.services;

import com.mp.restapiclient.dto.MeasurementDTODate;
import com.mp.restapiclient.models.Measurement;
import com.mp.restapiclient.models.Sensor;
import com.mp.restapiclient.repositories.MeasurementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void addMeasurements(Measurement measurement) {
        Sensor sensor = sensorService.findSensorByName(measurement.getSensor().getName()).get();
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }

    public List<Measurement> allMeasurements() {
        return measurementRepository.findAll();
    }

    public List<Measurement> rainyDays() {
        return measurementRepository.findMeasurementsByRainingIsTrue();
    }

    public int countRainyDays() {
        return measurementRepository.countAllByRainingIsTrue();
    }
}
