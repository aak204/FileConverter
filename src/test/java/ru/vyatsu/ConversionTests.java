package ru.vyatsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.converters.JSONtoXMLConverter;
import ru.vyatsu.service.converters.XMLtoJSONConverter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
    void testConvertXMLtoJSONCreatesFile() throws Exception {
        val converter = new XMLtoJSONConverter();
        val outputPath = Paths.get("src/test/resources/data.json");

        try (val inputStream = new FileInputStream("src/test/resources/data.xml");
             val outputStream = new ByteArrayOutputStream()) {

            converter.convert(inputStream, outputStream);
            Files.write(outputPath, outputStream.toByteArray());
        }

        assertThat(objectMapper.readTree(new File(outputPath.toString())),
                is(equalTo(objectMapper.readTree(new File("src/test/resources/data.json")))));
    }

    @Test
    void testConvertJSONtoXMLCreatesFile() throws Exception {
        val converter = new JSONtoXMLConverter();
        val outputPath = Paths.get("src/test/resources/data.xml");

        try (val inputStream = new FileInputStream("src/test/resources/data.json");
             val outputStream = new ByteArrayOutputStream()) {

            converter.convert(inputStream, outputStream);
            Files.write(outputPath, outputStream.toByteArray());
        }

        assertThat(xmlMapper.readTree(Files.newInputStream(outputPath)),
                is(equalTo(xmlMapper.readTree(new File("src/test/resources/data.xml")))));
    }
}
