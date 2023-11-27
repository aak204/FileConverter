package ru.vyatsu.service.factory;

import lombok.experimental.UtilityClass;
import ru.vyatsu.service.ConversionType;

@UtilityClass
public class TransformerFactoryProducer {
    @SuppressWarnings("unchecked")
    public <I, O> TransformerFactory<I, O> getFactory(final ConversionType type) {
        return switch (type) {
            case XML_TO_JSON -> (TransformerFactory<I, O>) new XMLtoJSONTransformerFactory();
            case JSON_TO_XML -> (TransformerFactory<I, O>) new JSONtoXMLTransformerFactory();
        };
    }
}