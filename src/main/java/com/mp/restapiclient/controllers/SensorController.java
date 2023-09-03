package com.mp.restapiclient.controllers;

import com.mp.restapiclient.dto.SensorDTO;
import com.mp.restapiclient.models.Sensor;
import com.mp.restapiclient.services.SensorService;
import com.mp.restapiclient.util.SensorErrorResponse;
import com.mp.restapiclient.util.SensorNotCreatedException;
import com.mp.restapiclient.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorValidator sensorValidator;
    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorValidator sensorValidator, SensorService sensorService, ModelMapper modelMapper) {
        this.sensorValidator = sensorValidator;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);

        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error: errors) {
                errorMsg.append(error.getField()).append(": ")
                        .append(error.getDefaultMessage()).append("; ");
            }
            throw new SensorNotCreatedException(errorMsg.toString());
        }

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> exceptionHandler(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
