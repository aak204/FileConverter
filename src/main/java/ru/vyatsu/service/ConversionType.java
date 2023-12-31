package ru.vyatsu.service;


public enum ConversionType {
    XML_TO_JSON, JSON_TO_XML;

    public static ConversionType fromInt(final int choice) {
        return switch (choice) {
            case 1 -> XML_TO_JSON;
            case 2 -> JSON_TO_XML;
            default -> throw new IllegalStateException();
        };
    }
}
