package ru.vyatsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.converters.JSONtoXMLConverter;
import ru.vyatsu.service.converters.XMLtoJSONConverter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
@FieldDefaults(makeFinal = true)
class ConversionTests {
    private ObjectMapper objectMapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();
    @Test
    void testConvertXMLtoJSON() throws Exception {
        val converter = new XMLtoJSONConverter();
        val expectedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/data.json")));

        try (val inputStream = new FileInputStream("src/test/resources/data.xml");
             val outputStream = new ByteArrayOutputStream()) {

            converter.convert(inputStream, outputStream);
            val resultJson = outputStream.toString(UTF_8);

            assertThat(objectMapper.readTree(resultJson),
                    is(equalTo(objectMapper.readTree(expectedJson))));
        }
    }


    @Test
    void testConvertJSONtoXML() throws Exception {
        val converter = new JSONtoXMLConverter();
        val expectedXml = new String(Files.readAllBytes(Paths.get("src/test/resources/data.xml")));

        try (val inputStream = new FileInputStream("src/test/resources/data.json");
             val outputStream = new ByteArrayOutputStream()) {

            converter.convert(inputStream, outputStream);
            val resultXml = outputStream.toString(UTF_8);

            assertThat(xmlMapper.readTree(resultXml),
                    is(equalTo(xmlMapper.readTree(expectedXml))));
        }
    }
}
