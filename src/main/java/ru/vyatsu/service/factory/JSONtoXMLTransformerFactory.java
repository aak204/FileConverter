package ru.vyatsu.service.factory;

import ru.vyatsu.service.transformers.JSONtoXMLTransformer;
import ru.vyatsu.service.structure.Brands;

class JSONtoXMLTransformerFactory implements TransformerFactory {
    @Override
    public Object transform(final Object input) {
        return JSONtoXMLTransformer.transform((Brands) input);
    }
}