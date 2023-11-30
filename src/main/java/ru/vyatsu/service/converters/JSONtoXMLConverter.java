package ru.vyatsu.service.converters;

import lombok.experimental.FieldDefaults;
import ru.vyatsu.service.factory.TransformerFactoryProducer;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.GarageXML;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static ru.vyatsu.service.ConversionType.JSON_TO_XML;

@FieldDefaults(makeFinal = true)
public class JSONtoXMLConverter extends Converter<Brands, GarageXML> {

    @Override
    protected Brands readValue(final InputStream inputStream) throws IOException {
        return objectMapper.readValue(inputStream, Brands.class);
    }

    @Override
    public GarageXML transform(final Brands input) {
        return (GarageXML) TransformerFactoryProducer.getFactory(JSON_TO_XML).transform(input);
    }

    @Override
    protected void writeValue(final OutputStream outputStream,final GarageXML output) throws IOException {
        outputStream.write(xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(output).getBytes(UTF_8));
    }
}