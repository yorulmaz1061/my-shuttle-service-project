package com.ozan.enums;

public enum AddressType {
    STUDENT("Student"),
    DRIVER("Driver"),
    HOSTESS("Hostess"),
    PARENT("Parent"),
    SCHOOL("School");
    private String value;

    AddressType(String value) {
        this.value = value;
    }
}
