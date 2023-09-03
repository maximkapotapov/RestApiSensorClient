package com.mp.restapiclient.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @Column(name = "measurement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int measurementId;

    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value can not be lower than -100")
    @Max(value = 100, message = "Value can not be higher than 100")
    private int value;

    @Column(name = "raining")
    @NotNull(message = "Raining should not be empty")
    private boolean raining;

    @ManyToOne
    @JoinColumn(columnDefinition = "sensor_name", referencedColumnName = "name")
    @NotNull(message = "Sensor can not be null")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Measurement() {}

    public Measurement(int value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementId=" + measurementId +
                ", value=" + value +
                ", raining=" + raining +
                ", createdAt=" + createdAt +
                '}' + "\n";
    }
}
