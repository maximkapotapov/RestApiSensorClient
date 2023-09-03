package com.mp.restapiclient.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MeasurementDTODate {
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value can not be lower than -100")
    @Max(value = 100, message = "Value can not be higher than 100")
    private int value;

    @NotNull(message = "Raining should not be empty")
    private boolean raining;

    @NotNull(message = "Sensor can not be null")
    private SensorDTO sensor;

    private LocalDateTime createdAt;

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

    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Measurements {" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                ", createdAt=" + createdAt +
                '}';
    }
}
