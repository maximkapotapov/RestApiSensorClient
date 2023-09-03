package com.mp.restapiclient.util;

import java.time.LocalDateTime;

public class MeasurementErrorResponse {
    private String message;
    private long dateTime;

    public MeasurementErrorResponse(String message, long dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}
