package com.cg.model.enums;

public enum EBill {

    ORDER("ORDER"),
    DONE("DONE"),
    CANCEL("CANCEL"),
    BOMB("BOMB");

    private final String value;

    EBill(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
