package com.ozan.exception;

public class VehicleCapacityExceededException extends RuntimeException {
    public VehicleCapacityExceededException(String message) {
        super(message);
    }
}
