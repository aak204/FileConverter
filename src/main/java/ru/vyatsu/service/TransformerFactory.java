package ru.vyatsu.service;

import ru.vyatsu.service.converters.JSONtoXMLTransformer;
import ru.vyatsu.service.converters.XMLtoJSONTransformer;

public class TransformerFactory {
    public static Transformer<?, ?> createTransformer(String type) {
        return switch (type.toUpperCase()) {
            case "XMLTOJSON" -> new XMLtoJSONTransformer();
            case "JSONTOXML" -> new JSONtoXMLTransformer();
            default -> throw new IllegalArgumentException("Unknown transformer type");
        };
    }
}
