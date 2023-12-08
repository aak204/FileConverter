package ru.vyatsu.service;

import lombok.experimental.StandardException;

@StandardException
public class ConversionException extends Exception {
    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionException(String message) {
        super(message);
    }
}