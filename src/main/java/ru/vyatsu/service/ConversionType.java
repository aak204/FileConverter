package ru.vyatsu.service;

public enum ConversionType {
    XML_TO_JSON, JSON_TO_XML, INVALID;

    public static ConversionType fromInt(int choice) {
        return switch (choice) {
            case 1 -> XML_TO_JSON;
            case 2 -> JSON_TO_XML;
            default -> INVALID;
        };
    }
}
