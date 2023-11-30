package ru.vyatsu.service.converters;

import ru.vyatsu.service.factory.TransformerFactoryProducer;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.GarageXML;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static ru.vyatsu.service.ConversionType.XML_TO_JSON;

public class XMLtoJSONConverter extends Converter<GarageXML, Brands> {

    @Override
    protected GarageXML readValue(final InputStream inputStream) throws IOException {
        return xmlMapper.readValue(inputStream, GarageXML.class);
    }

    @Override
    public Brands transform(final GarageXML input) {
        return (Brands) TransformerFactoryProducer.getFactory(XML_TO_JSON).transform(input);
    }

    @Override
    protected void writeValue(final OutputStream outputStream, final Brands output) throws IOException {
        outputStream.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(output).getBytes(UTF_8));
    }
}
