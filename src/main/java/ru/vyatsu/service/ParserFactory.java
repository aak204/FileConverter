package ru.vyatsu.service;

public class ParserFactory {
    public static Parser createParser(String type) {
        if (type.equalsIgnoreCase("XML")) {
            return new XMLParser();
        } else if (type.equalsIgnoreCase("JSON")) {
            return new JSONParser();
        }
        throw new IllegalArgumentException("Unknown parser type");
    }
}

