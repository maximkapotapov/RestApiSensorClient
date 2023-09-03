package com.mp.restapiclient.dto;

import com.mp.restapiclient.models.Sensor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class MeasurementDTO {

    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value can not be lower than -100")
    @Max(value = 100, message = "Value can not be higher than 100")
    private int value;

    @NotNull(message = "Raining should not be empty")
    private boolean raining;

    @NotNull(message = "Sensor can not be null")
    private SensorDTO sensor;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
