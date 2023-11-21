package ru.vyatsu.service.factory;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TransformerFactoryProducer {
    public TransformerFactory getFactory(final String type) {
        if ("XMLTOJSON".equalsIgnoreCase(type)) {
            return new XMLtoJSONTransformerFactory();
        } else if ("JSONTOXML".equalsIgnoreCase(type)) {
            return new JSONtoXMLTransformerFactory();
        }
        throw new IllegalArgumentException();
    }
}
