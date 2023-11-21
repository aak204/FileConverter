package ru.vyatsu.service.factory;

import ru.vyatsu.service.transformers.XMLtoJSONTransformer;
import ru.vyatsu.service.structure.GarageXML;

class XMLtoJSONTransformerFactory implements TransformerFactory {
    @Override
    public Object transform(final Object input) {
        return XMLtoJSONTransformer.transform((GarageXML) input);
    }
}