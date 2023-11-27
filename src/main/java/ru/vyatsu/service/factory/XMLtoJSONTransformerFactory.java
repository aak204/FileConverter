package ru.vyatsu.service.factory;

import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.transformers.XMLtoJSONTransformer;
import ru.vyatsu.service.structure.GarageXML;

class XMLtoJSONTransformerFactory implements TransformerFactory<GarageXML, Brands> {
    @Override
    public Brands transform(final GarageXML input) {
        return XMLtoJSONTransformer.transform(input);
    }
}