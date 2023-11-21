package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.converters.JSONtoXMLConverter;
import ru.vyatsu.service.converters.XMLtoJSONConverter;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertTrue;

class ConversionTests {
    @Test
    void testConvertXMLtoJSONCreatesFile() throws Exception {
        val converter = new XMLtoJSONConverter();
        val outputPath = Paths.get("src/test/resources/data.json");

        try (val inputStream = new FileInputStream("src/test/resources/data.xml");
             val outputStream = new ByteArrayOutputStream()) {

            converter.convert(inputStream, outputStream);
            Files.write(outputPath, outputStream.toByteArray());
        }

        assertTrue(Files.exists(outputPath));
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

        assertTrue(Files.exists(outputPath));
    }
}
