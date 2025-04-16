package com.ozan.enums;

public enum UserType {
    STUDENT("Student"),
    DRIVER("Driver"),
    HOSTESS("Hostess"),
    PARENT("Parent");

    private String value;
    UserType(String value) {
        this.value = value;
    }
}
