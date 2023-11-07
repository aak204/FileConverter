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
        public Object transform(final Object input) {
            return XMLtoJSONTransformer.transform((GarageXML) input);
        }
    },
    JSONTOXML {
        @Override
        public Object transform(final Object input) {
            return JSONtoXMLTransformer.transform((Brands) input);
        }
    };

    public abstract Object transform(final Object input);
}