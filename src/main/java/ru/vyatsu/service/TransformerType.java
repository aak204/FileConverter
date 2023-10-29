package ru.vyatsu.service;

import ru.vyatsu.service.converters.JSONtoXMLTransformer;
import ru.vyatsu.service.converters.XMLtoJSONTransformer;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.GarageXML;

/**
 * Enum определяет возможные типы трансформеров и их соответствующие методы преобразования
 */
public enum TransformerType {
    XMLTOJSON {
        @Override
        public Object transform(Object input) {
            XMLtoJSONTransformer transformer = new XMLtoJSONTransformer();
            return transformer.transform((GarageXML) input);
        }
    },
    JSONTOXML {
        @Override
        public Object transform(Object input) {
            JSONtoXMLTransformer transformer = new JSONtoXMLTransformer();
            return transformer.transform((Brands) input);
        }
    };

    public abstract Object transform(Object input);
}