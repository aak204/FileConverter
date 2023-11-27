package ru.vyatsu.service.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.FieldDefaults;
import ru.vyatsu.service.factory.TransformerFactoryProducer;
import ru.vyatsu.service.structure.Brands;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static ru.vyatsu.service.ConversionType.JSON_TO_XML;

@FieldDefaults(makeFinal = true)
public class JSONtoXMLConverter implements Converter {
    XmlMapper xmlMapper = new XmlMapper();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void convert(final InputStream inputStream,final OutputStream outputStream) throws IOException {
        outputStream.write(
                xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        TransformerFactoryProducer.getFactory(JSON_TO_XML)
                                .transform(objectMapper.readValue(inputStream, Brands.class)
                                ))
                                .getBytes(UTF_8)
                );
    }
}