package com.ozan.enums;

public enum Status {
    ACTIVE("Active"),
    PASSIVE("Passive");

    private String value;

    Status(String value) {
        this.value = value;
    }
}
