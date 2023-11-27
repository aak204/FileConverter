package ru.vyatsu.service.factory;

import ru.vyatsu.service.structure.GarageXML;
import ru.vyatsu.service.transformers.JSONtoXMLTransformer;
import ru.vyatsu.service.structure.Brands;

class JSONtoXMLTransformerFactory implements TransformerFactory<Brands, GarageXML> {
    @Override
    public GarageXML transform(final Brands input) {
        return JSONtoXMLTransformer.transform(input);
    }
}