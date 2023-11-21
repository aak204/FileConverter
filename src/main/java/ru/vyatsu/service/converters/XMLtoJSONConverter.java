package ru.vyatsu.service.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.FieldDefaults;
import ru.vyatsu.service.factory.TransformerFactoryProducer;
import ru.vyatsu.service.structure.GarageXML;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

@FieldDefaults(makeFinal = true)
public class XMLtoJSONConverter implements Converter {
    XmlMapper xmlMapper = new XmlMapper();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void convert(final InputStream inputStream,final OutputStream outputStream) throws IOException {
        outputStream.write(objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(TransformerFactoryProducer.getFactory("XMLTOJSON")
                        .transform(xmlMapper.readValue(inputStream, GarageXML.class))).getBytes(UTF_8));
    }
}