package ru.vyatsu.service;

import ru.vyatsu.service.converters.JSONtoXMLTransformer;
import ru.vyatsu.service.converters.XMLtoJSONTransformer;

/**
 * Enum определяет возможные типы трансформеров и их соответствующие методы преобразования
 */
public enum TransformerType {
    XMLTOJSON {
        @Override
        public Object transform(Object input) {
            XMLtoJSONTransformer transformer = new XMLtoJSONTransformer();
            return transformer.transform(input);
        }
    },
    JSONTOXML {
        @Override
        public Object transform(Object input) {
            JSONtoXMLTransformer transformer = new JSONtoXMLTransformer();
            return transformer.transform(input);
        }
    };

    public abstract Object transform(Object input);
}