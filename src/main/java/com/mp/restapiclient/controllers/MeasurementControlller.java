package com.mp.restapiclient.controllers;

import com.mp.restapiclient.dto.MeasurementDTO;
import com.mp.restapiclient.dto.MeasurementDTODate;
import com.mp.restapiclient.dto.SensorDTO;
import com.mp.restapiclient.models.Measurement;
import com.mp.restapiclient.models.Sensor;
import com.mp.restapiclient.services.MeasurementService;
import com.mp.restapiclient.util.MeasurementErrorResponse;
import com.mp.restapiclient.util.MeasurementNotCreatedException;
import com.mp.restapiclient.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementControlller {

    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementControlller(ModelMapper modelMapper, MeasurementValidator measurementValidator, MeasurementService measurementService) {
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);

        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors) {
                errorMsg.append(error.getField()).append(": ")
                        .append(error.getDefaultMessage()).append("; ");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }

        measurementService.addMeasurements(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTODate> allMeasurements() {
        return measurementService.allMeasurements()
                .stream().map(this::convertToMeasurementDTODate).collect(Collectors.toList());
    }

    @GetMapping("/rainy-days")
    public List<MeasurementDTODate> rainyDays() {
        return measurementService.rainyDays()
                .stream().map(this::convertToMeasurementDTODate).collect(Collectors.toList());
    }

    @GetMapping("/rainy-days-count")
    public int countRainyDays() {
        return measurementService.countRainyDays();
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTODate convertToMeasurementDTODate (Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTODate.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> exceptionHandler(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
